package com.lss.demo_order;

public class Order {
	private int id;
	private String date;
	private String lsh;
	private double sum;
	private int tid;
	private OrderDetail orderdateil;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public OrderDetail getOrderdateil() {
		return orderdateil;
	}
	public void setOrderdateil(OrderDetail orderdateil) {
		this.orderdateil = orderdateil;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", lsh=" + lsh + ", sum=" + sum + ", tid=" + tid
				+ ", orderdateil=" + orderdateil + "]";
	}
	
	

}
