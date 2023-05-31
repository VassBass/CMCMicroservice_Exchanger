package com.vassbassapp.CMCMicroservice_Exchanger.controller;

import com.vassbassapp.CMCMicroservice_Exchanger.repository.ExchangeRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExchangeController {

    private final ExchangeRepository repository;

    public ExchangeController(ExchangeRepository repository) {
        this.repository = repository;
    }

    @PutMapping("/add_content")
    public int addContent(@RequestBody String content) {
        try {
            return repository.addContent(content);
        } catch (InterruptedException e) {
            return 500;
        }
    }

    @PutMapping("/update_content/{id}")
    public boolean updateContent(@RequestBody String content, @PathVariable int id) {
        return repository.updateContent(id, content);
    }

    @GetMapping("/get_content/{id}")
    public String getContent(@PathVariable int id) {
        return repository.getContent(id);
    }

    @DeleteMapping("/delete_content/{id}")
    public boolean deleteContent(@PathVariable int id) {
        return repository.removeContent(id);
    }
}
