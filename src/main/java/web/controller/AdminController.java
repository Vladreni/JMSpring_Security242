package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("usersList", userService.listUsers());
        return "admin/users";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/new";
    }
//    public String newUserForm1(@ModelAttribute("user") User user) {
//
//        return "users/new";
//    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {

        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping("del/{id}")
    public String del(@PathVariable("id") Long id) {
        userService.del(userService.getUserById(id));
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String getOneUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("userOne", userService.getUserById(id));
        return "admin/user";
    }

}
