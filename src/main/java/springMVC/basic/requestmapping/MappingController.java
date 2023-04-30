package springMVC.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MappingController {

    //다중 url 매핑, 메서드 지정(GET)
    //다른 메서드를 이용한 요청이 오면 오류 출력
    @RequestMapping(value = {"/hello-basic", "/hello-basic-go"}, method = RequestMethod.GET)
    public String helloBasic() {
        log.info("helloBasic");

        return "ok";
    }

    //SpringBoot 3.0 이상부터는 '/hello-basic'과 '/hello-basic/'은 다른 URL
    @RequestMapping("/hello-basic/")
    public String helloBasic2() {
        log.info("helloBasic2");

        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /*
    [축약 어노테이션]
    -@GetMapping
    -@PostMapping
    -@PutMapping
    -@DeleteMappiG
    -@PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    //PathVariable : 경로 변수
    @GetMapping(value = "/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return data;
    }

    //다중 PathVariable, 변수 명과 경로 변수의 이름을 같게 하여 생략
    @GetMapping(value = "/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath2(@PathVariable String userId, @PathVariable String orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    //파라미터 조건 매핑 : parameter로 설정한 것이 있어야만 정상 출력(Postman)
    @GetMapping(value = "/mapping-param", params = "a=c")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    //헤더 조건 매핑 : 헤더에 추가 설정을 해야함 정상 출력(Postman)
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    //Content-Type 헤더 기반 추가 매핑(Postman)
    @PostMapping(value = "/mapping-consumes", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    //Accept 헤더 기반 추가 매핑(Postman) : Accept 헤더가 'application/json'같은 거면 오류
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduce() {
        log.info("mappingProduce");
        return "ok";
    }
}
