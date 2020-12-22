package com.unufolio.spring.security.demos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/11/26
 */
@Controller
public class MainController {

    // private final CsrfTokenRepository csrfTokenRepository;

    // public MainController(CsrfTokenRepository csrfTokenRepository) {this.csrfTokenRepository = csrfTokenRepository;}

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // @ModelAttribute("csrf")
    // public String csrf(HttpServletRequest request, HttpServletResponse response) {
    //     CsrfToken csrfToken = csrfTokenRepository.loadToken(request);
    //     if (csrfToken == null) {
    //         csrfToken = csrfTokenRepository.generateToken(request);
    //         csrfTokenRepository.saveToken(csrfToken, request, response);
    //     }
    //     return csrfToken.getToken();
    // }
}
