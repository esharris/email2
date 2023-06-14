package com.earl.email2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.earl.email2.account.EmailAccount;
import com.earl.email2.account.EmailAccountAddInput;
import com.earl.email2.account.EmailAccountRepository;
import com.earl.email2.account.EmailAccountUpdateInput;
import com.earl.email2.accountfactory.EmailAccountFactory;
import com.earl.email2.accountfactory.EmailAccountFactoryFactory;

@RestController
@RequestMapping("/emailaccounts")
public class EmailAccountController {

	private final EmailAccountRepository repository;

	private final EmailAccountFactoryFactory emailaccountFactoryFactory;

	private final RepresentationModelAssembler<EmailAccount, EntityModel<EmailAccount>> emailAccountModelAssembler;

	private final EmailAccountFactory emailAccountFactory;

	@Autowired
	public EmailAccountController(EmailAccountRepository repository,
			EmailAccountFactoryFactory emailaccountFactoryFactory,
			RepresentationModelAssembler<EmailAccount, EntityModel<EmailAccount>> emailAccountAssembler) {
		this.repository = repository;
		this.emailaccountFactoryFactory = emailaccountFactoryFactory;
		this.emailAccountModelAssembler = emailAccountAssembler;

		this.emailAccountFactory = this.emailaccountFactoryFactory.create(Email2Constants.DEFAULT_PASSWORD_LENGTH,
				Email2Constants.COMPANY_SUFFIX, Email2Constants.DEFAULT_MAILBOX_CAPACITY);
	}

	@GetMapping("")
	public CollectionModel<EntityModel<EmailAccount>> retrieveAllEmailAccounts() {
		List<EntityModel<EmailAccount>> emailAccounts = repository.findAll().stream()
				.map(emailAccountModelAssembler::toModel).toList();
		return CollectionModel.of(emailAccounts, //
				linkTo(methodOn(EmailAccountController.class).retrieveAllEmailAccounts()).withSelfRel());
	}

	@GetMapping("/{id}")
	public EntityModel<EmailAccount> getEmailAccountById(@PathVariable long id) {
		EmailAccount emailAccount = getEmailAccount(id);
		return emailAccountModelAssembler.toModel(emailAccount);
	}

	@GetMapping("/ln/{lastName}")
	public List<EntityModel<EmailAccount>> getEmailAccountByLastName(@PathVariable String lastName) {
		List<EmailAccount> emailAccountList = repository.findByLastName(lastName);
		return emailAccountList.stream().map(emailAccountModelAssembler::toModel).toList();
	}

	@PostMapping("")
	public ResponseEntity<?> newEmailAccount(@RequestBody EmailAccountAddInput emailAccountInput) {
		EmailAccount emailAccount = emailAccountFactory.create(emailAccountInput.firstName(),
				emailAccountInput.lastName(), emailAccountInput.department());
		EntityModel<?> entityModel = emailAccountModelAssembler.toModel(repository.save(emailAccount));
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	/**
	 * The user can’t modify the index. It uniquely identifies the email account.
	 * 
	 * @param emailAccountUpdateInput
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> replaceEmailAccount(@RequestBody EmailAccountUpdateInput emailAccountUpdateInput,
			@PathVariable Long id) {
		EmailAccount emailAccount = getEmailAccount(id);
		emailAccount.setFirstName(emailAccountUpdateInput.firstName());
		emailAccount.setLastName(emailAccountUpdateInput.lastName());
		emailAccount.setDepartment(emailAccountUpdateInput.department());
		emailAccount.setPassword(emailAccountUpdateInput.password());
		emailAccount.setMailboxCapacity(emailAccountUpdateInput.mailboxCapacity());
		emailAccount.setAlternateEmail(emailAccountUpdateInput.alternateEmail());
		repository.save(emailAccount);
		EntityModel<EmailAccount> entityModel = emailAccountModelAssembler.toModel(emailAccount);
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> partiallyReplaceEmailAccount(@RequestBody EmailAccountUpdateInput emailAccountUpdateInput,
			@PathVariable Long id) {
		EmailAccount emailAccount = getEmailAccount(id);
		if (emailAccountUpdateInput.firstName() != null) {
			emailAccount.setFirstName(emailAccountUpdateInput.firstName());
		}
		if (emailAccountUpdateInput.lastName() != null) {
			emailAccount.setLastName(emailAccountUpdateInput.lastName());
		}
		if (emailAccountUpdateInput.department() != null) {
			emailAccount.setDepartment(emailAccountUpdateInput.department());
		}
		if (emailAccountUpdateInput.password() != null) {
			emailAccount.setPassword(emailAccountUpdateInput.password());
		}
		if (emailAccountUpdateInput.mailboxCapacity() != null) {
			emailAccount.setMailboxCapacity(emailAccountUpdateInput.mailboxCapacity());
		}
		if (emailAccountUpdateInput.alternateEmail() != null) {
			emailAccount.setAlternateEmail(emailAccountUpdateInput.alternateEmail());
		}
		repository.save(emailAccount);
		EntityModel<EmailAccount> entityModel = emailAccountModelAssembler.toModel(emailAccount);
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmailAccount(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	private EmailAccount getEmailAccount(long id) {
		Optional<EmailAccount> findById = repository.findById(id);
		EmailAccount emailAccount = findById.orElseThrow(() -> new EmailAccountNotFoundException("id: " + id));
		return emailAccount;
	}
}
