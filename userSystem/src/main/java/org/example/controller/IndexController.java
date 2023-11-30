package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Karl Rules!
 * 2023/11/27
 * now File Encoding is UTF-8
 */
@Controller
public class IndexController {
    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "adminLogin";
    }
}
