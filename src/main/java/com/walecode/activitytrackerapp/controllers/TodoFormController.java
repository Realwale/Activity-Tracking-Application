package com.walecode.activitytrackerapp.controllers;

import com.walecode.activitytrackerapp.models.TodoItem;
import com.walecode.activitytrackerapp.services.TodoItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class TodoFormController {

    @Autowired
    private TodoItemService todoItemService;


    @GetMapping("/create-todo")
    public String showCreateForm(Model model, TodoItem todoItem) {
        TodoItem newTodo = new TodoItem();

        model.addAttribute("todoItem", newTodo);
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, Model model, HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
       Long id = (Long) httpSession.getAttribute("userNumber");

        TodoItem item = new TodoItem();
        model.addAttribute("todoItem");

        item.setDescription(todoItem.getDescription());
        item.setIsComplete(todoItem.getIsComplete());

        todoItemService.save(todoItem, id);
        return "redirect:/app";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
        TodoItem todoItem = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        todoItemService.delete(todoItem);
        return "redirect:/app";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        TodoItem todoItem = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        model.addAttribute("todo", todoItem);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid TodoItem todoItem, BindingResult result, Model model) {

        TodoItem item = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        item.setIsComplete(todoItem.getIsComplete());
        item.setDescription(todoItem.getDescription());

        todoItemService.save(item, id);

        return "redirect:/app";
    }
}
