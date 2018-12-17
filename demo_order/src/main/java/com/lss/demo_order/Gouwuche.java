package com.lss.demo_order;

public class Gouwuche {
	private String cname;
	private double cprice;
	private int num;
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public double getCprice() {
		return cprice;
	}
	public void setCprice(double cprice) {
		this.cprice = cprice;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "Gouwuche [cname=" + cname + ", cprice=" + cprice + ", num=" + num + "]";
	}
	

}
