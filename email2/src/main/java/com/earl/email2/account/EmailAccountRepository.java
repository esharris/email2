package com.earl.email2.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAccountRepository extends JpaRepository<EmailAccount, Long> {

	List<EmailAccount> findByLastName(String lastName);
}
