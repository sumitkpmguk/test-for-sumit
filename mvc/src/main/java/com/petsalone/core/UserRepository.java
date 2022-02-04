package com.petsalone.core;

import com.petsalone.servingwebcontent.PersistenceJPAConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRepository {

    public User findByUsername(String username) {
        EntityManager entityManager = getEntityManager();

        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(cb.equal(root.get(User_.username), username));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        List<User> results = query.getResultList();

        return results.isEmpty() ? null : results.get(0);
    }

    private EntityManager getEntityManager() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(PersistenceJPAConfig.class);
        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);

        return emf.createEntityManager();
    }
}
