package com.hwang.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ApplicationUserController {

    @Autowired
    private ApplicationUserRepository applicationUserRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Index Route
     * @param p
     * @param m
     * @return
     */
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String showHome(Principal p, Model m) {
        if(p != null){
            ApplicationUser loggedInUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();
            m.addAttribute("user", applicationUserRepo.findById(loggedInUser.id).get());
        } else{
            m.addAttribute("user", false);
        }
        return "index";
    }

    /**
     * Registration Route
     * @param p
     * @param m
     * @return
     */
    @RequestMapping(value= "/registration", method= RequestMethod.GET)
    public String showRegistration(Principal p, Model m) {
        ApplicationUser applicationUser = new ApplicationUser();
        m.addAttribute("user", applicationUser);
        return "registration";
    }

    /**
     * Signup route
     * @return
     */
    @RequestMapping(value= "/login", method= RequestMethod.GET)
    public String login(Principal p, Model m){
        if(p != null){
            ApplicationUser loggedInUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();
            m.addAttribute("user", applicationUserRepo.findById(loggedInUser.id).get());
        } else{
            m.addAttribute("user", false);
        }
        return "login";}



    @RequestMapping(value= "/registration", method= RequestMethod.POST)
    public RedirectView signUp(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String dateOfBirth,
                               @RequestParam String bio,
                               @RequestParam String username,
                               @RequestParam String password){
        ApplicationUser user = new ApplicationUser(firstName, lastName, dateOfBirth, bio, username, bCryptPasswordEncoder.encode(password));
        applicationUserRepo.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/myprofile");
    }

    @RequestMapping(value="/myprofile", method= RequestMethod.GET)
    public String showCurrentUserProfile(Principal p, Model m) {
       getUsername(p, m);
        ApplicationUser currentUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();


        List<Post> posts = applicationUserRepo.findById(currentUser.id).get().posts;
        if(posts.size() > 0) {m.addAttribute("posts", posts);}
        m.addAttribute("user", currentUser);
        return "profile";
    }

    @RequestMapping(value="/users/{userId}", method = RequestMethod.GET)
    public String showUsers(@PathVariable long userId, Model m, Principal p){
        getUsername(p, m);

        List<Post> posts = applicationUserRepo.findById(userId).get().posts;
        if (posts.size() > 0) {m.addAttribute("posts", posts);}
        m.addAttribute("user", applicationUserRepo.findById(userId).get());
        return "profile";
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public String getUsers(Principal p, Model m){

        getUsername(p, m);

        m.addAttribute("users", applicationUserRepo.findAll());

        return "users";
    }

    public void getUsername(Principal p, Model m) {
        if (p != null) {
            ApplicationUser currentUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();
            m.addAttribute("loggedInUser", applicationUserRepo.findById(currentUser.id).get());
        } else {
            m.addAttribute("loggedInUser", false);
        }
    }

}

