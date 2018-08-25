package com.financial.api.com.financial.api.controller;

import com.financial.api.com.financial.api.event.ResourceEvent;
import com.financial.api.com.financial.api.model.Launch;
import com.financial.api.com.financial.api.model.People;
import com.financial.api.com.financial.api.repository.LaunchRepository;
import com.financial.api.com.financial.api.repository.filter.LaunchFilter;
import com.financial.api.com.financial.api.repository.projection.SummaryLaunch;
import com.financial.api.com.financial.api.service.LaunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ROLE_SEARCH_LAUNCH') and #oauth2.hasScope('read')")
    public Page<Launch> search(LaunchFilter launchFilter, Pageable pageable){
        return launchRepository.filter(launchFilter, pageable);
    }

    /*projection, if in the request there is a parameter called "summary"  call this method*/
    @GetMapping(params = "summary")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_LAUNCH') and #oauth2.hasScope('read')")
    public Page<SummaryLaunch> summary(LaunchFilter launchFilter, Pageable pageable){
        return launchRepository.summary(launchFilter, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CREATE_LAUNCH') and #oauth2.hasScope('write')")
    public ResponseEntity<Launch> add(@Valid @RequestBody Launch launch, HttpServletResponse response) {
        Launch launchSave = launchService.save(launch);
        /*call event to put the Location to show the new category created in Headers*/
        applicationEventPublisher.publishEvent(new ResourceEvent(this, response, launchSave.getCode()));
        /*return 201 created*/
        return ResponseEntity.status(HttpStatus.CREATED).body(launchSave);
    }

    @GetMapping("/{code}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_LAUNCH') and #oauth2.hasScope('read')")
    public ResponseEntity<Launch> searchByCode(@PathVariable Long code) {
        Launch launch = launchRepository.findOne(code);
        return launch != null ? ResponseEntity.ok(launch) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVE_LAUNCH') and #oauth2.hasScope('write')")
    public void delete(@PathVariable Long code){
        launchRepository.delete(code);
    }




}
