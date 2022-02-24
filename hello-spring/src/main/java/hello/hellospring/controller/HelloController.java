package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name", required = true) String name, Model model) {
        // 위에서는 model.addAttribute로 "hello!!"를 지정함
        // 여기에서는 외부에서(웹에서) 받아옴
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody  // html의 <body>가 아나리 http(header/body)에서의 body부에 내가 직접 데이터 넣겠다는 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;  // 이 문자가 그대로 내려옴(html없음)
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello HelloAPI(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        // property접근방식 -> name이 private이므로 직접 사용할 수 없으므로
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
