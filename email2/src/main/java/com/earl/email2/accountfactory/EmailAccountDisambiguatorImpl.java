package com.earl.email2.accountfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailAccountDisambiguatorImpl implements EmailAccountDisambiguator {

	private final EmailAccountPartitionRepository repository;

	@Autowired
	public EmailAccountDisambiguatorImpl(EmailAccountPartitionRepository repository) {
		this.repository = repository;
	}

	/**
	 * This invocation is not idempotent. Each invocation updates an
	 * EmailAccountPartition persistent object.
	 */
	@Override
	public long generateUniqueCode(String firstName, String lastName, String department) {

		EmailAccountPartition emailAccountPartition = repository
				.findByFirstNameAndLastNameAndDepartment(firstName, lastName, department)
				.orElseGet(() -> new EmailAccountPartition(firstName, lastName, department, 0));
		emailAccountPartition.setCount(emailAccountPartition.getCount() + 1);
		repository.save(emailAccountPartition);
		return emailAccountPartition.getCount();
	}

	public EmailAccountPartitionRepository getRepository() {
		return repository;
	}
}
