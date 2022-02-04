package com.petsalone.servingwebcontent;

import com.petsalone.core.AbstractEntity;
import com.petsalone.core.AbstractEntity_;
import com.petsalone.core.My_Pet_Class;
import com.petsalone.core.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@EnableCaching(proxyTargetClass = true)
@EntityScan(basePackages = {"com.petsalone.core"})
public class ServingWebContentApplication {

	Logger logger = LoggerFactory.getLogger(ServingWebContentApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServingWebContentApplication.class, args);
	}

	@EventListener
	public void SeedData(ContextRefreshedEvent event) {

		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(PersistenceJPAConfig.class);
		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);

		EntityManager entityManager = emf.createEntityManager();

		entityManager.getTransaction().begin();

		if (!exists(My_Pet_Class.class, 1, entityManager)) {
			My_Pet_Class petOne = new My_Pet_Class();
			petOne.setName("Max");
			petOne.setPetType(2);
			petOne.setMissingSince(LocalDateTime.now().minusDays(6));

			entityManager.persist(petOne);
		}

		if (!exists(My_Pet_Class.class, 2, entityManager)) {
			My_Pet_Class petTwo = new My_Pet_Class();
			petTwo.setName("Fluffy");
			petTwo.setPetType(1);
			petTwo.setMissingSince(LocalDateTime.now().minusDays(10));

			entityManager.persist(petTwo);
		}

		if (!exists(My_Pet_Class.class, 3, entityManager)) {
			My_Pet_Class petThree = new My_Pet_Class();
			petThree.setName("Snowball");
			petThree.setPetType(4);
			petThree.setMissingSince(LocalDateTime.now().minusDays(2));

			entityManager.persist(petThree);
		}

		if (!exists(User.class, 1, entityManager)) {

			PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

			User user = new User();

			user.setUsername("elmyraduff");
			user.setPassword(encoder.encode("MontanaMax!!"));
			user.setEmail("elmyraduff@petsalone.com");

			entityManager.persist(user);
		}

		entityManager.getTransaction().commit();

	}

	public <E extends AbstractEntity> boolean exists(final Class<E> entityClass, final int id, EntityManager em) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<E> from = cq.from(entityClass);
		cq.select(cb.count(from));
		cq.where(cb.equal(from.get(AbstractEntity_.id), id));

		final TypedQuery<Long> tq = em.createQuery(cq);
		return tq.getSingleResult() > 0;
	}
}
