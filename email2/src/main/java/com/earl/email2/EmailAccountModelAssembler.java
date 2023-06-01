package com.earl.email2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.earl.email2.account.EmailAccount;

@Component
public class EmailAccountModelAssembler
		implements RepresentationModelAssembler<EmailAccount, EntityModel<EmailAccount>> {

	@Override
	public EntityModel<EmailAccount> toModel(EmailAccount entity) {
		return EntityModel.of(entity, //
				linkTo(methodOn(EmailAccountController.class).retrieveAllEmailAccounts()).withRel("emailAccounts"),
				linkTo(methodOn(EmailAccountController.class).getEmailAccountById(entity.getId())).withSelfRel());
	}

}
