package com.proj.signup.repo;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.proj.entity.quiz.UserMaster;

@Repository
public interface SignupRepository {
	public Boolean saveAll(UserMaster userMaster) throws SQLException;
}
