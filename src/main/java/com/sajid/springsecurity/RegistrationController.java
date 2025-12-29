package com.sajid.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("registration")
    public String registration() {
        return "registration";
    }
    @PostMapping("registration")
    public String registration(@ModelAttribute UserDto userDto) {
        IO.println(userDto);
        if(userDto.username().isEmpty()
                || userDto.password().isEmpty()
                || userDto.fullname().isEmpty()
                || userDto.confirmPassword().isEmpty()
                || userDto.email().isEmpty()
                || userDto.password().length() < 7
                || userDto.username().length() < 4
        ) {
            return "redirect:/registration?emptyField";
        }

        if (!userDto.password().equals(userDto.confirmPassword())) {
            return "redirect:/registration?wrongPassword";
        }
        if (!"on".equals(userDto.agreeToTerms())) {
            return "redirect:/registration?agreeToTerms";
        }

        userService.addUser(userDto);

        return "login";
    }

}
