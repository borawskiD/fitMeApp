package com.example.fitmeapp.UserAuthorization;

import com.example.fitmeapp.UserAuthorization.Gender;
import com.example.fitmeapp.UserAuthorization.UserRegistrationDto;
import com.example.fitmeapp.UserAuthorization.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthenticationController {
    UserService userService;
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "loginForm.html";
    }
    @GetMapping("/register")
    public String registerRedirect(Model model ){
        model.addAttribute("registerPerson", new UserRegistrationDto());
        return "registerForm.html";
    }
    @PostMapping("/register")
    public String register(@Valid UserRegistrationDto registerPerson, BindingResult bindingResult, int gender, Model model){
        if(bindingResult.hasErrors()){
            System.out.println("error: " + bindingResult.getErrorCount());
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error + "\n");
            }
            model.addAttribute("registerPerson", new UserRegistrationDto());
            return "registerForm";
        }


        Gender genderValue = switch (gender) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            default -> Gender.OTHER;
        };
        boolean success = userService.registerNewUser(registerPerson, genderValue);
        System.out.println("Success?" + success);
        if (success) return   "redirect:/login";
        else return "redirect:/register";
    }
    @GetMapping("/")
    public String home(){
        return "home.html";
    }
}
