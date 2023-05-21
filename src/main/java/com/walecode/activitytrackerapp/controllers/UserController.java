package com.walecode.activitytrackerapp.controllers;


import com.walecode.activitytrackerapp.dto.UserDTO;
import com.walecode.activitytrackerapp.models.TodoItem;
import com.walecode.activitytrackerapp.models.User;
import com.walecode.activitytrackerapp.repositories.UserRepository;
import com.walecode.activitytrackerapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/registration")
public class UserController {

    private final UserService userService;

    UserRepository userRepository;

    @ModelAttribute("user")
    public UserDTO userDTO(){
        return new UserDTO();
    }

    @GetMapping("/registration")
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount( UserDTO userDTO){
        userService.save(userDTO);
        return "redirect:/registration?success";
    }


    @GetMapping("/login")
    public ModelAndView loginPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("user", new UserDTO());
        return modelAndView;
    }

    @PostMapping("/login")
    public String loggedIn(UserDTO userDTO, HttpServletRequest httpServletRequest){
        TodoItem todoItem = new TodoItem();
        User user = userService.findUser(userDTO);
        if(user == null){
            return "login";
        }

        HttpSession httpSession = httpServletRequest.getSession(true);

        Long id = user.getId();

        httpSession.setAttribute("userNumber", id);
        return "redirect:/app";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        //httpSession.invalidate();
    httpSession.removeAttribute("userNumber");
        return "redirect:/";
    }
}
