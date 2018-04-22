package com.financial.api.com.financial.api.controller;

import com.financial.api.com.financial.api.event.ResourceEvent;
import com.financial.api.com.financial.api.model.Launch;
import com.financial.api.com.financial.api.model.People;
import com.financial.api.com.financial.api.repository.LaunchRepository;
import com.financial.api.com.financial.api.repository.filter.LaunchFilter;
import com.financial.api.com.financial.api.service.LaunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by netof on 11/03/2018.
 */
@RestController
@RequestMapping("/launch")
public class LaunchController {

    @Autowired
    private LaunchRepository launchRepository;

    @Autowired
    private LaunchService launchService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    public Page<Launch> search(LaunchFilter launchFilter, Pageable pageable){
        return launchRepository.filter(launchFilter, pageable);
    }

    @PostMapping
    public ResponseEntity<Launch> add(@Valid @RequestBody Launch launch, HttpServletResponse response) {
        Launch launchSave = launchService.save(launch);
        /*call event to put the Location to show the new category created in Headers*/
        applicationEventPublisher.publishEvent(new ResourceEvent(this, response, launchSave.getCode()));
        /*return 201 created*/
        return ResponseEntity.status(HttpStatus.CREATED).body(launchSave);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Launch> buscarPeloCodigo(@PathVariable Long code) {
        Launch launch = launchRepository.findOne(code);
        return launch != null ? ResponseEntity.ok(launch) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long code){
        launchRepository.delete(code);
    }


}
