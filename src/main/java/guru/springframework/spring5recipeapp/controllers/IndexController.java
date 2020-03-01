package guru.springframework.spring5recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    @RequestMapping({"", "/", "/index"})
    public String getRecipeIndexPage(){
        log.info("Calling index page from index controller");
        return "index";
    }
}
