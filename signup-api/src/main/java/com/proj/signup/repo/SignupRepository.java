package com.proj.signup.repo;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository
public interface SignupRepository {
	public Boolean saveAll(String userId, String userName, String userHashPassword, String firstName, String lastName,
			String emailId, String organisation, String university, String lastLogin, Date dob, String userRoleId,
			String addressId) throws SQLException;
}
