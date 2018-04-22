package com.financial.api.com.financial.api.repository;

import com.financial.api.com.financial.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by netof on 03/03/2018.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
