package com.earl.email2.accountfactory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAccountPartitionRepository extends JpaRepository<EmailAccountPartition, Long> {

	Optional<EmailAccountPartition> findByFirstNameAndLastNameAndDepartment(String firstName, String lastName,
			String department);
}
