package springMVC.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springMVC.basic.HelloData;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("request-param-v1 username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody //RestController 처럼 동작하도록 해줌
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge) {
        log.info("request-param-v2 username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age) {
        //파라미터와 변수명을 일치
        log.info("request-param-v3 username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        //요청 파라미터와 변수의 이름이 같으면 @RequestParam 생략 가능
        log.info("request-param-v4 username={}, age={}", username, age);
        return "ok";
    }

    //필수 파라미터 설정
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            //username은 필수, age는 선택
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {

        //요청 파라미터와 변수의 이름이 같으면 @RequestParam 생략 가능
        //파라미터 이름만 있고 값이 없는 경우 ==> username에 빈 문자열이 할당된다.
        log.info("request-param-required username={}, age={}", username, age);
        return "ok";
    }

    //파라미터 초기값 설정 ==> 초기값이 설정되었으므로 required는 의미가 없음
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            //username은 필수, age는 선택
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") Integer age) {

        //요청 파라미터와 변수의 이름이 같으면 @RequestParam 생략 가능
        //파라미터 이름만 있고 값이 없는 경우 ==> username에 빈 문자열이 할당된다.
        log.info("request-param-default username={}, age={}", username, age);
        return "ok";
    }

    //파라미터를 Map으로 조회
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, String> paramMap){
        log.info("request-param-default username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    //Model Attribute : 객체를 생성한 뒤, Setter를 이용하여 파라미터 값을 바인딩한다.
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("helloData={}", helloData);
        return "ok";
    }

}
