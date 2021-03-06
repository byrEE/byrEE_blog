package com.byrEE.controllers;

import com.byrEE.support.web.MessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * @author byrEE
 */
@Controller
public class UserController {

    @RequestMapping("signin")
    public String signin(Principal principal, RedirectAttributes ra) {
        return principal == null ? "users/signin" : "redirect:/";
    }
}