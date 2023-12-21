package com.nagarro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.apiData.ApiData;
import com.nagarro.apiData.PageInfo;
import com.nagarro.entities.UserEntity;

@Service
public class GenApiSerive {

	@Autowired
	private UserService userservice;

	public ApiData constructApiResponse(List<UserEntity> user, int limit, int offset) {

		PageInfo pageinfo = new PageInfo();
		int count = userservice.getUserCount();
		if (offset >= count) {
			System.out.println("offset can't be greater than count");
		}

		pageinfo.setTotal(count);
		pageinfo.setHasNextPage(limit + offset <= count);
		pageinfo.setHasPreviousPage(offset > 0);

		return new ApiData(user, pageinfo);
	}
}
