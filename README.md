# Spring-Boot-Starter-Setting

Spring-Boot-Starter-Setting은 많이 스프링 부트에서 많이 사용하는 라이브러리 및 기능을 적용한 예제 프로젝트입니다.

pring-Boot-Starter-Setting is an example project that applies libraries and features that are commonly used in spring boots.

적용된 라이브러리 및 기능 구성은 아래와 같습니다.
The applied library and feature configurations are shown below.

```bash
lucy-server-filter(sax-filter) - XSS 서블릿 필터(requestBody 적용 안됨)
ReadableRequestWrapperFilter - request.InputStream 데이터 휘발 방지
AOP - ASPECT-J를 사용. BootApplication에 @EnableAspectJAutoProxy 등록
lucy-requestBody-filter - AOP 방식으로 XSS requestBody 전용 필터 구현. @Xss, @XssExclude Annotation을 개발하여 디테일한 필터링이 가능해짐.
Database - Mysql 5.7 버전에 적용. 트랜잭션 설정 및 예제 파일 구현.
Flyway - 예제에 필요한 tb_test_info 테이블 생성. resource/db/migration 폴더 아래에 추가 기입하면 됨
Mybatis - xml 방식과 annotation 방식 모두 구현
Redis - Redis 구현
AWS S3 - AWS S3 기능 구현(생성, 삭제(하드, 소프트 선택가능)), static 경로 및 파일 명 겹치지 않도록 설정. 다중 업로드도 구현되어 있으나 Swagger 버그로 인하여 Swagger에서는 실행할 수 없음. POST MAN 으로 테스트 진행시 잘 동작하는 것을 확인함.
AWS SES - 메일 기능 구현, 메일 템플릿으로 thymeleaf 선정. 속도는 느린 편이나 자료가 많고 현재 많이 쓰이고 있음.
Thymeleaf - 설정 완료(SES 예제에서 실제 사용됨)
~Exception - Runtime Exception을 상속한 여러 Exception 구현, 중요도에 따라 Non Critical, Critical Exception을 구분함.
GlobalExceptionHandler - 스프링에서 발생하는 모든 Excetion에 대한 리턴 타입 , 메시지를 정돈하여 리턴함. 
SlackNotification - Critical Exception 혹은 예상치 못한 에러 발생 시 Slack에 Noti함. GlobalExceptionHandler에서 호출됨. url, requestParam, requestBody, ErrorMessage, ErrorStackTrace등의 정보를 담아 전송한다.
Swagger 2.9.2 - 3.0.0으로 버전업하려다 추후로 미룸. 현재 AWS S3 - 다중파일 업로드 시 에러 발생
CORS FILTER - WebConfig 파일에 설정되어 있음. 현재는 모든 접근을 허용함.
Scheduler - Scheduler의 로깅 능력을 향상시키기 위해 SchedulerConfig에 설정 적용. 실제 사용 예제는 org.tikim.boot.util.Scheduler 파일 내에 주석을 풀어주면 됨.
Validate - hibernate의 vaildate 기능을 사용. Test, Test2 domain에 적용되어 있다.
```
해당 프로젝트의 패키징 형태는 war 입니다.

## Installation

install Git, DBMS(example query consists of mysql), Redis, Maven 3

```bash
git clone ''

// Usage를 참고하여 application.properties 작성
// Create applications.properties by referring to Usage

// 프로젝트 경로에서
// in project path

mvn clean package war:war -DskipTests

// /tartget/ROOT.war 확인
// /tartget/ROOT.war check
```

## Usage


```python
// src/main/resource/application.example.config copy
// src/main/resource/application.config paste
// and modify application.config

//The code below is an example of the local environment.

```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

gmail : xhddlf8070@gmail.com
## License
[MIT](https://choosealicense.com/licenses/mit/)