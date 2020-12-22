package com.unufolio.spring.security.demos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/11/26
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
