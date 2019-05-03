package com.proj.signup.service;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.proj.signup.repo.SignupRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SignupService {

	private final SignupRepository signupRepository;

	public SignupService(SignupRepository signupRepository) {
		this.signupRepository = signupRepository;
	}

	public boolean insertUserDetails(String userId, String userName, String userPassword, String firstName,
			String lastName, String emailId, String organisation, String university, String lastLogin, Date dob,
			String userRoleId, String addressId) throws SQLException {
		log.info("START mathod insertUserDetails in SignupService");
		boolean check = signupRepository.saveAll(userId, userName, userPassword, firstName, lastName, emailId,
				organisation, university, lastLogin, dob, userRoleId, addressId);
		log.info("END mathod insertUserDetails in SignupService");
		return check;
	}
}
