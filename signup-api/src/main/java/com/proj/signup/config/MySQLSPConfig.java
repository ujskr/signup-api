package com.proj.signup.config;

import static com.proj.signup.constants.DatabaseConstants.IN_PARAMETER_IN_DOB;
import static com.proj.signup.constants.DatabaseConstants.IN_PARAMETER_IN_EMAIL_ID;
import static com.proj.signup.constants.DatabaseConstants.IN_PARAMETER_IN_FIRST_NAME;
import static com.proj.signup.constants.DatabaseConstants.IN_PARAMETER_IN_LAST_LOGIN;
import static com.proj.signup.constants.DatabaseConstants.IN_PARAMETER_IN_LAST_NAME;
import static com.proj.signup.constants.DatabaseConstants.IN_PARAMETER_IN_ORGANISATION;
import static com.proj.signup.constants.DatabaseConstants.IN_PARAMETER_IN_UNIVERSITY;
import static com.proj.signup.constants.DatabaseConstants.IN_PARAMETER_IN_USER_ID;
import static com.proj.signup.constants.DatabaseConstants.IN_PARAMETER_IN_USER_NAME;
import static com.proj.signup.constants.DatabaseConstants.IN_PARAMETER_IN_USER_PASSWORD;
import static com.proj.signup.constants.DatabaseConstants.IN_PARAMETER_IN_USER_ROLE_ID;
import static com.proj.signup.constants.DatabaseConstants.OUT_PARAMETER_OUT_PK_VALUE;
import static com.proj.signup.constants.DatabaseConstants.SCHEMA_NAME;
import static com.proj.signup.constants.DatabaseConstants.SP_NAME;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Configuration
public class MySQLSPConfig {

	private final DataSource dataSource;

	public MySQLSPConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean("createUSRPOSTSPInstance")
	public SimpleJdbcCall createUSRPOSTSPInstance() {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withSchemaName(SCHEMA_NAME)
				.withProcedureName(SP_NAME).declareParameters(new SqlParameter(IN_PARAMETER_IN_USER_ID, Types.VARCHAR))
				.declareParameters(new SqlParameter(IN_PARAMETER_IN_USER_NAME, Types.VARCHAR))
				.declareParameters(new SqlParameter(IN_PARAMETER_IN_USER_PASSWORD, Types.VARCHAR))
				.declareParameters(new SqlParameter(IN_PARAMETER_IN_FIRST_NAME, Types.VARCHAR))
				.declareParameters(new SqlParameter(IN_PARAMETER_IN_LAST_NAME, Types.VARCHAR))
				.declareParameters(new SqlParameter(IN_PARAMETER_IN_EMAIL_ID, Types.VARCHAR))
				.declareParameters(new SqlParameter(IN_PARAMETER_IN_ORGANISATION, Types.VARCHAR))
				.declareParameters(new SqlParameter(IN_PARAMETER_IN_UNIVERSITY, Types.VARCHAR))
				.declareParameters(new SqlParameter(IN_PARAMETER_IN_LAST_LOGIN, Types.VARCHAR))
				.declareParameters(new SqlParameter(IN_PARAMETER_IN_DOB, Types.DATE))
				.declareParameters(new SqlParameter(IN_PARAMETER_IN_USER_ROLE_ID, Types.VARCHAR))
				.declareParameters(new SqlOutParameter(OUT_PARAMETER_OUT_PK_VALUE, Types.VARCHAR));
		simpleJdbcCall.compile();
		return simpleJdbcCall;
	}

}
