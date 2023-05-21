package com.walecode.activitytrackerapp.controllers;


import com.walecode.activitytrackerapp.dto.UserDTO;
import com.walecode.activitytrackerapp.services.TodoItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class HomeController {


    private final TodoItemService todoItemService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/app")
    public ModelAndView index(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        Long id = (Long) httpSession.getAttribute("userNumber");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("user", new UserDTO());
        modelAndView.addObject("todoItems", todoItemService.getAll(id));
        return modelAndView;
    }

}