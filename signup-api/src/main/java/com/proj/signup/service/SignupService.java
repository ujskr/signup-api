package com.proj.signup.service;

import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proj.entity.quiz.UserMaster;
import com.proj.signup.repo.SignupRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SignupService {

	private final SignupRepository signupRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public SignupService(SignupRepository signupRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.signupRepository = signupRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public boolean insertUserDetails(UserMaster userMaster) throws SQLException {
		log.info("START method insertUserDetails in SignupService");
		String userHashPassword;
		// userHashPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());
		userHashPassword = bCryptPasswordEncoder.encode(userMaster.getUserPassword());
		userMaster.setUserPassword(userHashPassword);
		boolean check = signupRepository.saveAll(userMaster);
		log.info("END method insertUserDetails in SignupService");
		return check;
	}
}
