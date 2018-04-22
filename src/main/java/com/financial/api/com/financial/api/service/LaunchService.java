package com.financial.api.com.financial.api.service;

import com.financial.api.com.financial.api.model.Launch;
import com.financial.api.com.financial.api.model.People;
import com.financial.api.com.financial.api.repository.LaunchRepository;
import com.financial.api.com.financial.api.repository.PeopleRepository;
import com.financial.api.com.financial.api.service.exception.PeopleNotExistOrNotActiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by netof on 12/03/2018.
 */
@Service
public class LaunchService {


    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private LaunchRepository launchRepository;

    public Launch save(Launch launch){
        People people = peopleRepository.findOne(launch.getPeople().getCode());
        if(people == null || people.isNotActive()){
            throw new PeopleNotExistOrNotActiveException();
        }
        return launchRepository.save(launch);
    }
}
