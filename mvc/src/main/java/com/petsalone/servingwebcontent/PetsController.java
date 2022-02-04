package com.petsalone.servingwebcontent;

import com.petsalone.core.My_Pet_Class;
import com.petsalone.core.PetsService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Controller
public class PetsController {

	@GetMapping("/")
	public String index(Model model) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersistenceJPAConfig.class);
		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
		EntityManager entityManager = emf.createEntityManager();

		PetsService service = new PetsService();
		List<My_Pet_Class> result = service.GetAll(entityManager);

		return "pets/index";
	}

}
