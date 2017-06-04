package com.elecom.crawler.entity;

public class ProductPrice extends Entity{

	private int product_price_id;
	private String product_no;
	private String shop_id;
	private String product_price;
	private String product_image_url;
	private String product_link_url;
	public int getProduct_price_id() {
		return product_price_id;
	}
	public void setProduct_price_id(int product_price_id) {
		this.product_price_id = product_price_id;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public String getProduct_price() {
		return product_price;
	}
	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}
	public String getProduct_image_url() {
		return product_image_url;
	}
	public void setProduct_image_url(String product_image_url) {
		this.product_image_url = product_image_url;
	}
	public String getProduct_link_url() {
		return product_link_url;
	}
	public void setProduct_link_url(String product_link_url) {
		this.product_link_url = product_link_url;
	}
	
}
