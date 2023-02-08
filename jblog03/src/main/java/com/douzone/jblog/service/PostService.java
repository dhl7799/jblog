package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	public void post(PostVo pv) {
		postRepository.post(pv);
	}

	public List<PostVo> getPostByCategoryNo(long categoryNo) {
		return postRepository.getPostByCategoryNo(categoryNo);
	}

	public long countByCategoryNo(long no) {
		return postRepository.countByCategoryNo(no);
	}
}
