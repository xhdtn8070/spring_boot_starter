package org.tikim.boot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.tikim.boot.domain.slack.SlackAttachment;
import org.tikim.boot.domain.slack.SlackParameter;
import org.tikim.boot.domain.slack.SlackTarget;
import org.tikim.boot.enums.ErrorMessage;
import org.tikim.boot.exception.BaseException;
import org.tikim.boot.exception.CriticalException;
import org.tikim.boot.exception.NonCriticalException;
import org.tikim.boot.util.Parser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Resource
	SlackNotiSender slackNotiSender;
	
	@Value("${slack.nofity_error_url}")
	private String notifyErrorUrl;
	
	/*
	 * 크리티컬한 에러만 노티를 보낸다
	 *
	 */
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<BaseException> defaultException(Throwable e,HandlerMethod handlerMethod) throws IOException {
		BaseException baseException = null;
		Boolean slack = false;
		if (e instanceof NonCriticalException){
			((NonCriticalException) e).setErrorTrace(e.getStackTrace()[0].toString());
			baseException = (NonCriticalException) e;
		}

		if(e instanceof CriticalException){
			((CriticalException) e).setErrorTrace(e.getStackTrace()[0].toString());
			baseException = (CriticalException) e;
			slack=true;
		}

		if(baseException == null){
			baseException = new BaseException(e.getClass().getSimpleName(), ErrorMessage.UNDEFINED_EXCEPTION);
			baseException.setErrorMessage(e.getMessage());
			baseException.setErrorTrace(e.getStackTrace()[0].toString());
			slack = true;
		}

		if(slack.equals(true)){
			sendSlackNoti(e,handlerMethod);
		}
		return new ResponseEntity<>(baseException,HttpStatus.BAD_REQUEST);
	}

	private <T extends Throwable> void sendSlackNoti(T e,HandlerMethod handlerMethod) throws IOException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		// URL = HOST + URI
		// ex) URL = boot.tikim.org/profile/v1/sample
		String host = request.getHeader("host"); // boot.tikim.org
		String uri = request.getRequestURI(); // /v1/sample

		SlackTarget slackTarget = new SlackTarget(notifyErrorUrl,"");
		SlackParameter slackParameter = new SlackParameter();
		slackParameter.setText(String.format("`%s` 서버에서 에러가 발생했습니다.", host));
		SlackAttachment slackAttachment = new SlackAttachment();
		String errorName = e.getClass().getSimpleName();
		String errorFile = e.getStackTrace()[0].getFileName();
		String errorMessage = e.getMessage();
		int errorLine = e.getStackTrace()[0].getLineNumber();

		String requestBody = IOUtils.toString(request.getReader());
		String requestParam = new ObjectMapper().writeValueAsString(Parser.splitQueryString(request.getQueryString()));

		String message = String.format("```%s %s Line %d```\n```===== [Message] ===== \n%s\n\n===== [Controller] =====\n%s\n\n===== [RequestParameter] =====\n%s\n\n===== [RequestBody] =====\n%s```",
				errorName, errorFile, errorLine, errorMessage, handlerMethod, requestParam, requestBody);

		slackAttachment.setTitle(String.format("URI : %s", uri));
		slackAttachment.setText(message);
		slackParameter.getSlackAttachments().add(slackAttachment);
		slackNotiSender.send(slackTarget,slackParameter);
	}
	
}
