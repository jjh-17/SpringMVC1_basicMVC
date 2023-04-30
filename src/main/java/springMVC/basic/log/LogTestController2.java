package springMVC.basic.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
Controller : 반환 값이 String이면 뷰 이름으로 인식
RestController : 반환 값이 String이면 HTTP 메시지 바디에 바로 입력
 */

@Slf4j //log 객체 생성을 생략 가능
@RestController
public class LogTestController2 {

    @RequestMapping("/log-test2")
    public String logTest() {
        String name = "log-test2";

        System.out.println("name = " + name);

        //로그 레벨
        //이때, '{}'가 아닌 '+'를 하면 로그 출력 레벨과 상관없이 + 연산을 수행 ==> '{}' 방식을 쓸 것!
        log.trace("trace log={}", name);
        log.debug("debug log={}", name); //개발자 들이 주로 보는 디버그 정보
        log.info("info log={}", name); //중요 정보
        log.warn("warn log={}", name); //경보
        log.error("error log={}", name); //에러

        return "ok";
    }
}
