package springMVC.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import springMVC.basic.HelloData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        //문자로 된 JSON 데이터를 ObjectMapper를 이용하여 자바 객체로 변환
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        log.info("request-body-json-v1 messageBody={}", messageBody);
        log.info("request-body-json-v1 username={}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }


    //@RequestBody로 Http 메시지 바디 정보 조회
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("request-body-json-v2 messageBody={}", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("request-body-json-v2 username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    //@RequestBody를 이용하여 HTTP 메시지 바디 정보를 조회하고, 우리가 원하는 형식으로 변환
    //HelloData는 사용자 지정 클래스이므로 @RequestBody 생략 시 @ModelAttribute가 적용됨 ==> HTTP 메시지 바디 대신 요청 파라미터 처리
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        log.info("request-body-json-v3 username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    //HttpEntity 사용
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> data) {
        HelloData helloData = data.getBody();
        log.info("request-body-json-v4 username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    //@ResponseBody가 반환 객체를 응답 HTTP 메시지 바디에 넣어줌
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {
        log.info("request-body-json-v5 username={}, age={}", helloData.getUsername(), helloData.getAge());
        return helloData;
    }
}
