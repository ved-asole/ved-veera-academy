package com.ghostcoderz.vedveeracoaching.controller;

import com.ghostcoderz.vedveeracoaching.entity.SecurityUser;
import com.ghostcoderz.vedveeracoaching.service.UserService;
import com.ghostcoderz.vedveeracoaching.util.email.EmailSenderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    private final UserService userService;

    private final EmailSenderService emailSenderService;

    public HomeController(UserService userService, EmailSenderService emailSenderService) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
    }

    @GetMapping("/")
    public ModelAndView displayHomepage(){
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

    @GetMapping("/addAdminUser")
    public SecurityUser addAdminUser(){
        return userService.saveAdminUser();
    }

    @GetMapping("/contact-us")
    public ModelAndView displayContactUsPage(){
        ModelAndView model = new ModelAndView();
        model.setViewName("contactUs");
        return model;
    }

    @GetMapping("/about")
    public ModelAndView displayAboutUsPage(){
        ModelAndView model = new ModelAndView();
        model.setViewName("aboutUs");
        return model;
    }

    @GetMapping("/sendEmail")
    public ModelAndView sendEmail(@RequestParam(name = "email") String email){

        emailSenderService.sendSimpleEmail(
                email,
                "Testing email from VedVeera Academy",
                "This is s test email from VedVeera Academy. " +
                        "Please let us know once you get the email.");

        ModelAndView model = new ModelAndView();
        model.setViewName("email");

        return model;
    }

}