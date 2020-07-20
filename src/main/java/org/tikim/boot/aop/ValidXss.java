package org.tikim.boot.aop;

import com.nhncorp.lucy.security.xss.LucyXssFilter;
import com.nhncorp.lucy.security.xss.XssSaxFilter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.tikim.boot.annotation.Xss;
import org.tikim.boot.annotation.XssExclude;
import org.tikim.boot.enums.ErrorMessage;
import org.tikim.boot.exception.CriticalException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
/**
 * 김통일에 의해 개발되었으며, 자유롭게 사용, 수정이 가능하다.
 * xhddlf8070@gmail.com
 * 해당 VaildXss는 String.class Type만 타켓팅하여 필터링한다.
 *
 * Class : org.tikim.boot.domain.test.Test
 * Class : org.tikim.boot.domain.test.Test2
 * 위 2개의 Class를 예시로 사용하고 있다.
 *
 * 결과 사진 : https://static-sample.tikim.org/2020/07/20/28659dc7-634c-46b7-9678-09a7e2c36d86-1595235709288.PNG
 * 사진의 왼쪽이 before
 * 사진의 오른쪽이 after
 *
 *   사용가능한 형태
 *
 * 	    requestBody의 Type이 String인 경우
 *      requestBody의 Type에 @Xss annotation이 붙어 있는 경우
 *          - @Xss가 선언되고 있는 Type 내부의 Field Type이 String인 경우
 *              * 해당 Field에 @XssExclude가 붙어있는 경우 필터링에서 제외된다.
 *
 *          - @Xss가 선언되고 있는 Type 내부의 Field Type이 @Xss annotation이 붙어있는 Type인 경우
 *              * private Test2 test2;   -> Test2 Type 상단에 @Xss Annotation이 붙어있는 경우
 *
 *          - @Xss가 선언되고 있는 Type 내부의 Field Type이 Collection<? has annotation @Xss>인 경우
 *              * private List<Test2> test2List;
 *
 *          - @Xss가 선언되고 있는 Type 내부의 Field Type이 Object[]인 경우. 단 Object 대상은 @Xss가 선언되어 있어야한다.
 *              * private Test2[] test2Arrays;
 *
 *   예외 사항
 *
 * List<String> testStringList;
 *          - @Xss가 선언되고 있는 Type 내부의 Field Type이 Collection<String>인 경우
 * 	            * private List<String> testStringList;
 *
 *          - @Xss가 선언되고 있는 Type 내부의 Field Type이 String[]인 경우
 * 	            * private String[] testStringArrays;
 */
@Component
@Aspect
public class ValidXss {

    private LucyXssFilter filter = XssSaxFilter.getInstance("lucy-xss-sax.xml",true);

	@Around(value = "@annotation(org.tikim.boot.annotation.Xss)")
	public Object validxss(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Annotation[][] annotationMatrix = methodSignature.getMethod().getParameterAnnotations();
        int index = -1;
        for(Annotation[] annotations : annotationMatrix){
            index++;
            for(Annotation annotation : annotations){
                if(!(annotation instanceof RequestBody)){
                    continue;
                }
                proceedingJoinPoint.getArgs()[index] = xssCheck(proceedingJoinPoint.getArgs()[index]);

                return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
            }
        }
		return proceedingJoinPoint.proceed();
	}

	private Object xssCheck(Object vo) throws Exception {
        if(vo==null) return vo;
        if(vo.getClass() == String.class) return filter.doFilter((String) vo);

        BeanInfo info = Introspector.getBeanInfo(vo.getClass(),Object.class);

        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            Method reader = pd.getReadMethod();

            if (reader != null ) {
                Object object = reader.invoke(vo);

                if (object != null && object.getClass().isAnnotationPresent(Xss.class)) {
                    xssCheck(object);
                    continue;
                }

                if (object != null && object instanceof Collection) {
                    for (Object o : ((Collection<Object>) object)) {
                        xssCheck(o);
                    }
                }
                if (object != null && object.getClass().isArray()) {
                    for (Object o : ((Object[]) object)) {
                        xssCheck(o);
                    }
                }

                if (object != null && object.getClass() == String.class) {
                    if (vo.getClass().getDeclaredField(getFieldName(reader)).isAnnotationPresent(XssExclude.class)) {
                        continue;
                    }
                    Method setter = vo.getClass().getMethod(getSetterMethodString(reader), String.class);
                    setter.invoke(vo, filter.doFilter(object.toString()));
                    continue;
                }

            }
        }
        return vo;
    }
    private String getSetterMethodString(Method getterMethod){
        String getterMethodName = getterMethod.getName();
        if(!getterMethodName.startsWith("get")){
            throw new CriticalException(ErrorMessage.AOP_XSS_SETTER_NO_EXSISTS_EXCEPTION);
        }
        String setterMethodName = getterMethodName.replaceFirst("get","set");
        return setterMethodName;
    }

    private String getFieldName(Method method){
	    String methodName = method.getName();
        if(methodName.startsWith("get")||method.getName().startsWith("set")){
            return methodName.substring(3,4).toLowerCase()+ methodName.substring(4,methodName.length());
        }
        throw new CriticalException(ErrorMessage.AOP_XSS_FIELD_NO_EXSISTS_EXCEPTION);
    }

}