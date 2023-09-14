package com.example.fitmeapp;

import com.example.fitmeapp.UserAuthorization.Gender;
import com.example.fitmeapp.UserAuthorization.UserRegistrationDto;
import com.example.fitmeapp.UserAuthorization.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;

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
    public String registerRedirect(){
        return "registerForm.html";
    }
    @PostMapping("/register")
    public ModelAndView register(String firstName, String lastName, String email, String password, String confirmPassword, LocalDate birthDate, int weight, int height, int exercise, int gender, ModelMap modelMap){
        Gender genderValue = switch (gender) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            default -> Gender.OTHER;
        };
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(firstName, lastName, email, password, confirmPassword, weight, height, birthDate, exercise, genderValue);
        boolean success = userService.registerNewUser(userRegistrationDto);
        if (success) return new ModelAndView("redirect:/login");
        else return new ModelAndView("redirect:/register");
    }
}
