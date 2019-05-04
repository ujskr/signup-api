package com.proj.signup.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proj.entity.quiz.UserMaster;
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
	private ResponseEntity<Boolean> createUser(@RequestBody UserMaster userMaster) throws SQLException {
		log.info("START method createUser in SignupController");
		Boolean check = signupService.insertUserDetails(userMaster);
		log.info("END method createUser in SignupController");
		return new ResponseEntity<>(check, HttpStatus.OK);
	}
}
