package br.inf.audasi.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author renatomoitinhodias@gmail.com
 */
@Controller
public class HomeController {

    public @RequestMapping("/") String home() {
        return "redirect:swagger-ui.html";
    }
}
