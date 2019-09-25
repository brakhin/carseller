package ru.bgbrakhi.carseller.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class Controller403 {

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accesssDenied(Principal principal, ModelMap model) {
        model.addAttribute("login", principal == null ? "" : String.format(" [ %s ]", principal.getName()));
        model.addAttribute("msg", "Нет прав для доступа к этой странице!");
        return "403";
    }}
