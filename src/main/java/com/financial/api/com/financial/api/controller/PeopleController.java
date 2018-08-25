package com.financial.api.com.financial.api.controller;

import com.financial.api.com.financial.api.event.ResourceEvent;
import com.financial.api.com.financial.api.model.People;
import com.financial.api.com.financial.api.repository.PeopleRepository;
import com.financial.api.com.financial.api.service.PeopleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by netof on 05/03/2018.
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PEOPLE') and #oauth2.hasScope('read')")
    public List<People> findAll(){
        return peopleRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CREATE_PEOPLE') and #oauth2.hasScope('write')")
    public ResponseEntity<People> add(@Valid @RequestBody People people, HttpServletResponse response) {
        People peopleSave = peopleRepository.save(people);
        /*call event to put the Location to show the new category created in Headers*/
        applicationEventPublisher.publishEvent
                (new ResourceEvent(this, response, peopleSave.getCode()));
        /*return 201 created*/
        return ResponseEntity.status(HttpStatus.CREATED).body(peopleSave);
    }

    @GetMapping("/{code}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PEOPLE') and #oauth2.hasScope('read')")
    public ResponseEntity<People> buscarPeloCodigo(@PathVariable Long code) {
        People people = peopleRepository.findOne(code);
        return people != null ? ResponseEntity.ok(people) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long code){
        peopleRepository.delete(code);
    }

    @PutMapping("/{code}")
    @PreAuthorize("hasAuthority('ROLE_CREATE_PEOPLE') and #oauth2.hasScope('write')")
    public ResponseEntity<People> updatePeople(@PathVariable Long code,@Valid @RequestBody People people){
       People peopleSaved = peopleService.updatePeople(code, people);
       return ResponseEntity.ok(peopleSaved);
    }

    @PutMapping("/{code}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_CREATE_PEOPLE') and #oauth2.hasScope('write')")
    public void updatePeopleActiveProperty(@PathVariable Long code,@RequestBody Boolean active){
        peopleService.updateActiveProperty(code, active);
    }
}
