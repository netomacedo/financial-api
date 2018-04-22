package com.financial.api.com.financial.api.repository;

import com.financial.api.com.financial.api.model.People;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by netof on 05/03/2018.
 */
public interface PeopleRepository extends JpaRepository<People, Long> {
}
