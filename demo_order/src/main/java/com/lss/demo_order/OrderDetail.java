package com.lss.demo_order;

public class OrderDetail {
	private int id;
	private int cid;
	private int oid;
	private double price;
	private Caipin caipin;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Caipin getCaipin() {
		return caipin;
	}
	public void setCaipin(Caipin caipin) {
		this.caipin = caipin;
	}
	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", cid=" + cid + ", oid=" + oid + ", price=" + price + ", caipin=" + caipin
				+ "]";
	}
	

}
