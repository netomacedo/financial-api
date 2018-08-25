package com.financial.api.com.financial.api.repository.launch;

import com.financial.api.com.financial.api.model.Launch;
import com.financial.api.com.financial.api.repository.filter.LaunchFilter;
import com.financial.api.com.financial.api.repository.projection.SummaryLaunch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by netof on 13/03/2018.
 */
public interface LaunchRepositoryQuery {

    public Page<Launch> filter(LaunchFilter launchFilter, Pageable pageable);
    public Page<SummaryLaunch> summary(LaunchFilter launchFilter, Pageable pageable);

}

