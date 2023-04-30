package springMVC.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    //MultiValueMap : 하나의 Key가 여러 Value를 가질 수 있다.
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod, //현재 HTTP 메서드 형식
                          Locale locale, //언어
                          @RequestHeader MultiValueMap<String, String> headerMap, //모든 HTTP 정보를 MultiValueMap 형식으로 조회
                          @RequestHeader("host") String host, //HTTP 내 'host'헤더 정보
                          @CookieValue(value = "myCookie", required = false) String cookie //쿠키(required : 필수 값 여부)
    ) {

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("cookie={}", cookie);

        return "ok";
    }
}
