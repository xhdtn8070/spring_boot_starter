package org.tikim.boot.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.tikim.boot.domain.slack.SlackParameter;
import org.tikim.boot.domain.slack.SlackTarget;

import javax.annotation.Resource;


@Configuration
public class SlackNotiSender {
	@Resource
	RestTemplate restTemplate;
	
	public void send(SlackTarget slackTarget, SlackParameter slackParameter) {
		if(checkUrl(slackTarget.getWebHookUrl())) {
            try {
            	System.out.println(new ObjectMapper().writeValueAsString(slackParameter));
            	restTemplate.postForObject(slackTarget.getWebHookUrl(),new ObjectMapper().writeValueAsString(slackParameter),String.class);
            } catch (RestClientException | JsonProcessingException e) {
            	e.printStackTrace();
            }
		}
	}
	private boolean checkUrl(String url) {
		if(url==null||url.equals("")) {
			return false;
		}
		return true;
	}
}
