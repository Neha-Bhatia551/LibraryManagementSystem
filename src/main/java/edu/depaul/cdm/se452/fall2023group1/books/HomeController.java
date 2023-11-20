package edu.depaul.cdm.se452.fall2023group1.books;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/book")
public class HomeController {

    @GetMapping
    public String index() {
        return  "books/list";
    }
}

