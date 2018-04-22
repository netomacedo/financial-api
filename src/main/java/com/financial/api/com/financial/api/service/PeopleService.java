package com.financial.api.com.financial.api.service;

import com.financial.api.com.financial.api.model.People;
import com.financial.api.com.financial.api.repository.PeopleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by netof on 10/03/2018.
 */
@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    public People updatePeople(Long code, People people){
        People peopleFromDb = getPeopleByCode(code);
        BeanUtils.copyProperties(people, peopleFromDb,"code");//copy to new people without code
        return peopleRepository.save(peopleFromDb);
    }

    public void updateActiveProperty(Long code, Boolean active){
        People peopleFromDb = getPeopleByCode(code);
        peopleFromDb.setActive(active);
        peopleRepository.save(peopleFromDb);
    }

    public People getPeopleByCode(Long code) {
        People peopleFromDb = peopleRepository.findOne(code);
        if(peopleFromDb == null){
            throw new EmptyResultDataAccessException(1);
        }
        return peopleFromDb;
    }


}
