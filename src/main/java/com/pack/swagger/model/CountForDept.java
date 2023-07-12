package com.pack.swagger.model;

public class CountForDept {
	
	private long count;
	private String Dept;
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String getDept() {
		return Dept;
	}
	public void setDept(String dept) {
		Dept = dept;
	}
	public CountForDept(long count, String dept) {
		super();
		this.count = count;
		Dept = dept;
	}
	public CountForDept() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CountForDept [count=" + count + ", Dept=" + Dept + "]";
	}
	
}
