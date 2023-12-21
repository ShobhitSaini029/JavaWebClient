package com.nagarro.apiData;

import java.util.List;

import com.nagarro.entities.UserEntity;
public class ApiData {
	
	private List<UserEntity> data;
	private PageInfo pageinfo;
	
	public List<UserEntity> getData() {
		return data;
	}
	public void setData(List<UserEntity> data) {
		this.data = data;
	}
	public PageInfo getPageinfo() {
		return pageinfo;
	}
	public void setPageinfo(PageInfo pageinfo) {
		this.pageinfo = pageinfo;
	}
	
	public ApiData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ApiData(List<UserEntity> data, PageInfo pageinfo) {
		super();
		this.data = data;
		this.pageinfo = pageinfo;
	}
	
	@Override
	public String toString() {
		return "ApiResponse [data=" + data + ", pageinfo=" + pageinfo + "]";
	}
	
	

}
