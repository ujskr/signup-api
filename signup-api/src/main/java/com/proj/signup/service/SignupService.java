package com.proj.signup.service;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

	public boolean insertUserDetails(String userId, String userName, String userPassword, String firstName,
			String lastName, String emailId, String organisation, String university, String lastLogin, Date dob,
			String userRoleId, String addressId) throws SQLException {
		log.info("START method insertUserDetails in SignupService");
		String userHashPassword;
		//userHashPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());
		userHashPassword = bCryptPasswordEncoder.encode(userPassword);
		boolean check = signupRepository.saveAll(userId, userName, userHashPassword, firstName, lastName, emailId,
				organisation, university, lastLogin, dob, userRoleId, addressId);
		log.info("END method insertUserDetails in SignupService");
		return check;
	}
}
