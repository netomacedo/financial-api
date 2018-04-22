package com.financial.api.com.financial.api.repository;

import com.financial.api.com.financial.api.model.Launch;
import com.financial.api.com.financial.api.repository.launch.LaunchRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by netof on 11/03/2018.
 */
public interface LaunchRepository extends JpaRepository<Launch, Long>, LaunchRepositoryQuery{
}
