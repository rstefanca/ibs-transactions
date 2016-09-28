package cz.codingmonkeys.ibs;

import cz.codingmonkeys.ibs.domain.DirectChannelUser;
import cz.codingmonkeys.ibs.repositories.DirectChannelUserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Richard Stefanca
 */

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	ApplicationRunner commandLineRunner(final DirectChannelUserRepository patientRepository) {

		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments applicationArguments) throws Exception {
				DirectChannelUser dcu = new DirectChannelUser("1FA");
				patientRepository.save(dcu);
				System.out.println(dcu);
			}
		};
	}
}
