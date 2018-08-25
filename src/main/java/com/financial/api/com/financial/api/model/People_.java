package com.financial.api.com.financial.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by netof on 25/08/2018.
 */
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(People.class)
public abstract class People_ {

    public static volatile SingularAttribute<People, Long> code;
    public static volatile SingularAttribute<People, Boolean> active;
    public static volatile SingularAttribute<People, Address> address;
    public static volatile SingularAttribute<People, String> name;
}
