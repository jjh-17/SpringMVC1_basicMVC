package springMVC.basic.response;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import springMVC.basic.HelloData;

import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {

    //HttpServletResponse 객체를 통해 HTTP 메시지 바디에 직접 응답 메시지 전달
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        log.info("response-body-string-v1");
        response.getWriter().write("ok");
    }

    //HttpEntity: HTTP  메시지 헤더/바디 정보를 가짐
    //ResponseEntity: HttpEntity 상속. 추가적으로 HTTP 응답 코드 설정 가능
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() throws IOException {
        log.info("response-body-string-v2");
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    //@ResponseBody: View 사용 X, HTTP 메시지 컨버터를 통해 HTTP 메시지 바디에 데이터 직접 입력
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        log.info("response-body-string-v3");
        return "ok";
    }

    //ResponseEntity 반환. HTTP 메시지 컨버터를 통해 JSON 형식으로 변환하여 반환
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        log.info("response-body-json-v1");
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    //@ResponseStatus로 응답 코드 설정
    //@ResponseBody로 HTTP 메시지 입력
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        log.info("response-body-json-v2");
        return helloData;
    }
}
