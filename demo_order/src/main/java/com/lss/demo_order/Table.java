package com.lss.demo_order;

public class Table {
	private int id;
	private int tnum;
	private String state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTnum() {
		return tnum;
	}
	public void setTnum(int tnum) {
		this.tnum = tnum;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Table [id=" + id + ", tnum=" + tnum + ", state=" + state + "]";
	}
	

}
