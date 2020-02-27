package com.example.thtodolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    TodoRepository todoRepository;

    @RequestMapping("/")
    public String listJobs(Model model){
        // Model class, model object, add to model...
        // todos key, with value: all of the repository for todas...

        model.addAttribute("todos", todoRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String todoForm(Model model){
        model.addAttribute("todo", new Todo());
        return "todoform";
    }

    @PostMapping("/process")
    // valid anno, based on bean anno; class and object, class and object
    // the bean class is validated, ... binding result is connected with @Valid
    public String processForm(@Valid Todo todo, BindingResult result){
        // is the object valid? we need two objects, with anno
        // at valid with obj to be tests; then class and object of tester...
        if (result.hasErrors()){
            // the BindingResult class gives its object the method of hasErrors()
            return "todoform";
        }
        todoRepository.save(todo);
        // the CrudRepo interface gives my repo... the methhod to save
        return "redirect:/";
        // redirect means it has no unique destination, but will instead go to an already assigned one
        // this means the destination is hidden, a process happens without seeing it apart from an
        // already designated page
    }

    @RequestMapping("/detail/{id}")
    public String showTodo(@PathVariable("id") long id, Model model)
            // what is the pathvar? this is...
            // the user enters a requested doto, and the id is selected....
            //
    {
        model.addAttribute("todo", todoRepository.findById(id).get());
        // why .get? because we're not simply taking the entire list
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id,
                               Model model){
        model.addAttribute("todo", todoRepository.findById(id).get());
        return "todoform";
    }

    @RequestMapping("/delete/{id}")
    public String delTodo(@PathVariable("id") long id){
        todoRepository.deleteById(id);
        return "redirect:/";
    }
}

// what are these path vars? somehow, when user clicks something...
// the id is selected and used, to get a certain one

// by choosing a link with the @{},  @{/update/{id}(id=${course.id})}
//  @{/path/{the path var}(path var is... ${}





