package oi.projet.springboot.ImmobilierApp;

import oi.projet.springboot.ImmobilierApp.models.Administrateur;
import oi.projet.springboot.ImmobilierApp.models.User;
import oi.projet.springboot.ImmobilierApp.repository.AdministrateurRepository;
import oi.projet.springboot.ImmobilierApp.repository.LocataireRepository;
import oi.projet.springboot.ImmobilierApp.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "oi.projet.springboot.ImmobilierApp.models") // Ajoute cette ligne si ce n’est pas le cas
@EnableJpaRepositories(basePackages = "oi.projet.springboot.ImmobilierApp.repository")
public class ImmobilierAppApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(ImmobilierAppApplication.class, args);
		UserRepository administrateurRepository =
				configurableApplicationContext.getBean(AdministrateurRepository.class);
		UserRepository locataireRepository =
				configurableApplicationContext.getBean(LocataireRepository.class);

	}

}
