package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    public WelcomeController(@Value("${welcome.message}") String input){
        this.message=input;
    }

    public String message;
    @GetMapping("/")
    public String sayHello() {
        return message;
    }
}