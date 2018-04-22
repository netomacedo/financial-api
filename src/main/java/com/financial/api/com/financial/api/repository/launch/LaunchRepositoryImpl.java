package com.financial.api.com.financial.api.repository.launch;

import com.financial.api.com.financial.api.model.Launch;
import com.financial.api.com.financial.api.repository.filter.LaunchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by netof on 15/03/2018.
 */
public class LaunchRepositoryImpl implements  LaunchRepositoryQuery{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Launch> filter(LaunchFilter launchFilter, Pageable pageable) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Launch> criteria = criteriaBuilder.createQuery(Launch.class);
        Root<Launch> root = criteria.from(Launch.class);

        //restrictions
        Predicate[] predicates = createRestrictions(launchFilter, criteriaBuilder, root);
        criteria.where(predicates);

        TypedQuery<Launch> launchTypedQuery = entityManager.createQuery(criteria);
        addRestrictionsPagination(launchTypedQuery, pageable);

        return new PageImpl<>(launchTypedQuery.getResultList(), pageable, total(launchFilter));
    }

    private Predicate[] createRestrictions(LaunchFilter launchFilter, CriteriaBuilder criteriaBuilder,
                                           Root<Launch> root) {

        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isEmpty(launchFilter.getDescription())){
           predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" +
                   launchFilter.getDescription().toLowerCase() + "%"));
        }
        if(launchFilter.getDueDateFrom() != null){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dueDate"), launchFilter.getDueDateFrom()));

        }
        if(launchFilter.getDueDateTo() != null){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dueDate"), launchFilter.getDueDateTo()));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void addRestrictionsPagination(TypedQuery<Launch> launchTypedQuery, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalItensPerPage = pageable.getPageSize();
        int firstItemPage = currentPage * totalItensPerPage;

        launchTypedQuery.setFirstResult(firstItemPage);
        launchTypedQuery.setMaxResults(totalItensPerPage);

    }

    private Long total(LaunchFilter launchFilter){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Launch> root = criteria.from(Launch.class);

        Predicate[] predicates = createRestrictions(launchFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return entityManager.createQuery(criteria).getSingleResult();
    }


}
