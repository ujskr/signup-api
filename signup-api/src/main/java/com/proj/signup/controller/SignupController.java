package com.proj.signup.controller;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.proj.signup.service.SignupService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SignupController {

	private final SignupService signupService;

	public SignupController(SignupService signupService) {
		this.signupService = signupService;
	}

	@PostMapping("/signup/user")
	private ResponseEntity<Boolean> createUser(@RequestHeader String userId, @RequestHeader String userName,
			@RequestHeader String userPassword, @RequestHeader String firstName, @RequestHeader String lastName,
			@RequestHeader String emailId, @RequestHeader String organisation, @RequestHeader String university,
			@RequestHeader String lastLogin, @RequestHeader(required = false) Date dob,
			@RequestHeader String userRoleId, @RequestHeader String addressId) throws SQLException {
		log.info("START method createUser in SignupController");
		Boolean check = signupService.insertUserDetails(userId, userName, userPassword, firstName, lastName, emailId,
				organisation, university, lastLogin, dob, userRoleId, addressId);
		log.info("END method createUser in SignupController");
		return new ResponseEntity<>(check,HttpStatus.OK);
	}
}
