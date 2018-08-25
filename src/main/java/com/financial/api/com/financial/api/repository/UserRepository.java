package com.financial.api.com.financial.api.repository;

import com.financial.api.com.financial.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by netof on 22/08/2018.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);

}
