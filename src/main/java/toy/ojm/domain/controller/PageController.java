package toy.ojm.domain.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {

    @Value("${kakao.appkey}")
    private String kakaoAppKey;

    @GetMapping("/")
    public String home() {
        return "recommend";
    }

    @GetMapping("/pageForm1")
    public String pageForm() {
        return "fragments/page-form-1";
    }

    @GetMapping("/pageForm2")
    public String pageForm2() {
        return "fragments/page-form-2";
    }

    @GetMapping("/pageForm3")
    public String pageForm3() {
        return "fragments/page-form-3";
    }

    @GetMapping("/page1")
    public String page() {
        return "page1";
    }

    @GetMapping("/page2")
    public String page2(Model model) {
        model.addAttribute("kakaoAppKey", kakaoAppKey);
        return "page2";
    }

    @GetMapping("/page3")
    public String page3() {
        return "page3";
    }

    @GetMapping("/page4")
    public String page4() {
        return "page4";
    }


    @GetMapping("/location")
    public String location() {
        return "location";
    }

    @GetMapping("/map0")
    public String map0() {
        return "map/map0";
    }

    @GetMapping("/map1")
    public String map2() {
        return "map/map1";
    }

    @GetMapping("/map3")
    public String map3() {
        return "map/map3";
    }

    @GetMapping("/map4")
    public String map4() {
        return "map/map4";
    }

    @GetMapping("/locationSample")
    public String locationSample() {
        return "map/geolocationSample";
    }

    @GetMapping("/result")
    public String result() {
        return "result";
    }

    @GetMapping("/result2")
    public String result2() {
        return "result2";
    }

    @GetMapping("/result3")
    public String result3() {
        return "result3";
    }

    @GetMapping("/recommendation")
    public String recommendation() {
        return "result2";
    }
}
