package springMVC.basic.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ResponseViewController {

    //ModelAndView 반환
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView modelAndView = new ModelAndView("response/hello")
                .addObject("data", "response view v1");

        return modelAndView;
    }

    //String 반환
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "response view v2");
        return "response/hello"; //View의 논리적 위치 반환
    }

    //@Controller 사용, HTTP 메시지 바디 처리 파라미터가 없음 ==> @RequestMapping된 경로를 View의 논리적 위치로 판단
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "response view v3");
    }
}
