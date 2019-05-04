package com.proj.signup.repo.impl;

import static com.proj.signup.constants.DatabaseConstants.OUT_PARAMETER_OUT_PK_VALUE;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.proj.signup.repo.SignupRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SignupRepositoryImpl implements SignupRepository {

	private final SimpleJdbcCall createUSRPOSTSPInstance;

	public SignupRepositoryImpl(@Qualifier("createUSRPOSTSPInstance") SimpleJdbcCall createUSRPOSTSPInstance) {
		this.createUSRPOSTSPInstance = createUSRPOSTSPInstance;
	}

	@Override
	public Boolean saveAll(String userId, String userName, String userPassword, String firstName, String lastName,
			String emailId, String organisation, String university, String lastLogin, Date dob, String userRoleId,
			String addressId) throws SQLException {
		Boolean check = false;
		try {
			log.info("START method saveAll in SignupRepository");
			dob = new Date();
			DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
			dateFormat.format(dob);
			Map<String, Object> result = createUSRPOSTSPInstance.execute(userId, userName, userPassword, firstName,
					lastName, emailId, organisation, university, lastLogin, dob, userRoleId, addressId);
			String res = (String) result.get(OUT_PARAMETER_OUT_PK_VALUE);

			if (StringUtils.isEmpty(res)) {
				check = false;
			} else if (userId.equals(res)) {
				check = true;
			} else {
				check = false;
			}
		} catch (UncategorizedSQLException ex) {
			log.info("error occoured in saving data - " + ex.getLocalizedMessage());
		}
		log.info("END method saveAll in SignupRepository");
		return check;
	}

}
