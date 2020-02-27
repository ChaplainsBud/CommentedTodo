package com.example.thtodolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    TodoRepository repository;

    @Override
    public void run(String... strings) throws Exception{
        // ... run? String... spread operator?

        Todo todo;
        todo = new Todo("Laundry", "Use the machines");
        repository.save(todo);
        // the repository is an obj for the class...
        // and you can save onto it

        todo = new Todo("Cook", "Make dinner");
        repository.save(todo);
    }
}
