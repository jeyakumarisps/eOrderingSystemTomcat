package org.bigbites.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.bigbites.main.exception.ResourceNotFoundException;
import org.bigbites.main.model.Dish;
import org.bigbites.main.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ApplicationController {
    
    @Autowired
    DishRepository dishRepository;

    @GetMapping("/dishes")
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    @PostMapping("/addDish")
    public Dish createDish(@Valid @RequestBody Dish dish) {
        return dishRepository.save(dish);
    }
    
    @GetMapping("/dishes/{id}")
    public Dish getNoteById(@PathVariable(value = "id") Long dishId) {
        return dishRepository.findById(dishId)
                .orElseThrow(() -> new ResourceNotFoundException("Dish", "id", dishId));
    }

}
