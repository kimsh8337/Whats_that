package com.web.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dictionary.dao.UserDao;
import com.web.dictionary.dto.SignupRequest;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired UserDao dao;
	
	public int checkOverlapEmail(String email) throws Exception {
		return dao.checkOverlapEmail(email) ;
	}

	@Override
	public boolean saveAuthcode(String email, String code) {
		System.out.println("service " + email);
		System.out.println("service " + code);
		return dao.saveAuthcode(email,code);
	}

	@Override
	public boolean signUp(SignupRequest request) {
		return dao.signUp(request);
	}

	@Override
	public int checkEmailandCode(String email, String code) {
		return dao.checkEmailandCode(email,code);
	}

	@Override
	public void deleteAuthcode(String email) {
		dao.deleteAuthcode(email);
	}

	@Override
	public int checkOverlapAuthcode(String email) {
		return dao.checkOverlapAuthcode(email);
	}
	
}