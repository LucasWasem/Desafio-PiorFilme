package br.com.desafio.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Classe de controller redireciona para a interface do swagger.
 *
 * @author lucas
 * @since 27/06/2020
 */
@Controller
@RequestMapping("/")
public class IndexController {
    
    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }
}
