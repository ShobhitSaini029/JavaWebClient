package com.nagarro.apiData;

public class PageInfo {
	
	private boolean hasNextPage;
	private boolean hasPreviousPage;
	private int total;
	
	
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public PageInfo(boolean hasNextPage, boolean hasPreviousPage, int total) {
		super();
		this.hasNextPage = hasNextPage;
		this.hasPreviousPage = hasPreviousPage;
		this.total = total;
	}
	public PageInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PageInfo [hasNextPage=" + hasNextPage + ", hasPreviousPage=" + hasPreviousPage + ", total=" + total
				+ "]";
	}

}
