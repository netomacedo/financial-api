package com.financial.api.com.financial.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by netof on 25/08/2018.
 */
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Launch.class)
public abstract class Launch_ {

        public static volatile SingularAttribute<Launch, Long> code;
        public static volatile SingularAttribute<Launch, String> observation;
        public static volatile SingularAttribute<Launch, LaunchType> type;
        public static volatile SingularAttribute<Launch, LocalDate> paymentDate;
        public static volatile SingularAttribute<Launch, People> people;
        public static volatile SingularAttribute<Launch, LocalDate> dueDate;
        public static volatile SingularAttribute<Launch, Category> category;
        public static volatile SingularAttribute<Launch, BigDecimal> value;
        public static volatile SingularAttribute<Launch, String> description;
}
