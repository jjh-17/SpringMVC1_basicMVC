package springMVC.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    //HTTP 메시지 바디에 단순 텍스트를 담아서 post
    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//inputStream(Byte 코드)를 UTF-8로 변형

        log.info("request-body-string-v1 messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    //매개변수로 InputStream, Writer
    //InputStream(Reader) : HTTP 요청 메시지 바디의 내용을 직접 조회
    //OutputStream(Writer) : HTTP 응답 에시지의 바디에 직접 결과 출력
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer writer) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//inputStream(Byte 코드)를 UTF-8로 변형
        log.info("request-body-string-v2 messageBody={}", messageBody);

        writer.write("ok");
    }

    //HttpEntity : 메시지 바디 정보 직접 조회 ==> 요청 파라미터 조회 기능과 관계 없음
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("request-body-string-v3 messageBody={}", messageBody);

        return new HttpEntity<>("ok"); //응답에도 사용 가능
    }

    //@RequestBody : Http 메시지 바디 정보 조회
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
        log.info("request-body-string-v4 messageBody={}", messageBody);
        return "ok";
    }
}
