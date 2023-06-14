package com.earl.email2;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.earl.email2.account.EmailAccount;
import com.earl.email2.account.EmailAccountRepository;
import com.earl.email2.accountfactory.EmailAccountFactory;
import com.earl.email2.accountfactory.EmailAccountFactoryFactory;

@SpringBootApplication
public class Email2Application {

	private static final Logger log = LoggerFactory.getLogger(Email2Application.class);

	private final EmailAccountFactoryFactory emailAccountFactoryFactory;

	@Autowired
	public Email2Application(EmailAccountFactoryFactory emailAccountFactoryFactory) {
		this.emailAccountFactoryFactory = emailAccountFactoryFactory;
	}

	public static void main(String[] args) {
		SpringApplication.run(Email2Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmailAccountRepository repository) {
		final EmailAccountFactory emailAccountFactory = emailAccountFactoryFactory.create(
				Email2Constants.DEFAULT_PASSWORD_LENGTH, Email2Constants.COMPANY_SUFFIX,
				Email2Constants.DEFAULT_MAILBOX_CAPACITY);
		return (args) -> {
			repository.save(emailAccountFactory.create("Jordan", "Harris", "sales"));
			repository.save(emailAccountFactory.create("Senge", "Schove", "acct"));
			repository.save(emailAccountFactory.create("Earl", "Harris", "dev"));
			repository.save(emailAccountFactory.create("John", "Smith", "custservice"));

			log.info("EmailAcounts found with findAll():");
			log.info("-------------------------------");
			for (EmailAccount emailAccount : repository.findAll()) {
				log.info(emailAccount.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Optional<EmailAccount> emailAccount = repository.findById(1L);
			log.info("EmailAcounts found with findById(1L):");
			log.info("--------------------------------");
			log.info(emailAccount.isPresent() ? emailAccount.get().toString() : "not found");
			log.info("");

			log.info("EmailAcounts found with findByLastName('Harris'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Harris").forEach(emailAccount1 -> {
				log.info(emailAccount1.toString());
			});
			log.info("");
		};
	}

}
