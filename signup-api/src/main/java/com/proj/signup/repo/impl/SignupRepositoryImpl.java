package com.proj.signup.repo.impl;

import static com.proj.signup.constants.DatabaseConstants.OUT_PARAMETER_OUT_PK_VALUE;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.proj.entity.quiz.UserMaster;
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
	public Boolean saveAll(UserMaster userMaster) throws SQLException {
		Boolean check = false;
		try {
			log.info("START method saveAll in SignupRepository");
			Map<String, Object> result = createUSRPOSTSPInstance.execute(userMaster.getUserId(),
					userMaster.getUserName(), userMaster.getUserPassword(), userMaster.getFirstName(),
					userMaster.getLastName(), userMaster.getEmailId(), userMaster.getOrganisation(),
					userMaster.getUniversity(), userMaster.getLastLogin(), userMaster.getDob(),
					userMaster.getUserRole().getUserRoleId(), userMaster.getUserAddress().getAddressId());
			String res = (String) result.get(OUT_PARAMETER_OUT_PK_VALUE);

			if (StringUtils.isEmpty(res)) {
				check = false;
			} else if (userMaster.getUserId().equals(res)) {
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
