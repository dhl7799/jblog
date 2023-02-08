package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void join(UserVo vo) {
		long no = userRepository.getNo()+1;
		vo.setNo(no);
		userRepository.join(vo);
	}

	public UserVo getUserByIdAndPassword(String id, String password) {
		return userRepository.getUserByIdAndPassword(id,password);
	}

}
