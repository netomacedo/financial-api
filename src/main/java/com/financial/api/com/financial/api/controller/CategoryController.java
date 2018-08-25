package com.financial.api.com.financial.api.controller;

import com.financial.api.com.financial.api.event.ResourceEvent;
import com.financial.api.com.financial.api.model.Category;
import com.financial.api.com.financial.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by netof on 03/03/2018.
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CREATE_CATEGORY') and #oauth2.hasScope('write')")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category, HttpServletResponse response){
        Category categorySave = categoryRepository.save(category);

        /*call event to put the Location to show the new category created in Headers*/
        applicationEventPublisher.publishEvent
                (new ResourceEvent(this, response, categorySave.getCode()));
         /*return 201 created*/
        return ResponseEntity.status(HttpStatus.CREATED).body(categorySave);
    }

    @GetMapping("/{code}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_CATEGORY')and #oauth2.hasScope('read')")
    public ResponseEntity<Category> findById(@PathVariable Long code) {
        Category category = categoryRepository.findOne(code);
        if (category != null) {
            ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
        return null;
    }
}
