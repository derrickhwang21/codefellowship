package com.hwang.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
public class ApplicationUserController {

    @Autowired
    private ApplicationUserRepository applicationUserRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String showHome() {
        return "index";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistration() {
        return "registration";
    }


    @RequestMapping(value = "/perform_signup", method = RequestMethod.POST)
    public RedirectView signUp(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam Date dateOfBirth,
                               @RequestParam String bio,
                               @RequestParam String userName,
                               @RequestParam String passWord,
                               Model m){
        ApplicationUser user = new ApplicationUser(firstName, lastName, dateOfBirth, bio, userName, bCryptPasswordEncoder.encode(passWord));
        applicationUserRepo.save(user);
        return new RedirectView("/users/" + user.id);
    }

    @RequestMapping(value="/users/{userId}", method = RequestMethod.GET)
    public String showUsers(@PathVariable long userId, Model m){
        m.addAttribute("user", applicationUserRepo.findById(userId).get());
        return "users";
    }



}
