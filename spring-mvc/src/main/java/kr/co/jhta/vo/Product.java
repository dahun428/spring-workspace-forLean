package kr.co.jhta.vo;

import java.util.Date;

public class Product {
/*
 *     product_no number(10) primary key,
    category_id varchar2(100) references spring_categories(category_id),
    product_name varchar(500) not null,
    product_price number(10) not null,
    product_discount_price number(10) not null,
    product_stock number(10) default 100,
    product_created_date date default sysdate
    
 */
	
	private int no;
	private Category category;
	private String name;
	private long price;
	private long discountPrice;
	private long stock;
	private Date createdDate;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(long discountPrice) {
		this.discountPrice = discountPrice;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Product [no=" + no + ", category=" + category + ", name=" + name + ", price=" + price
				+ ", discountPrice=" + discountPrice + ", stock=" + stock + ", createdDate=" + createdDate + "]";
	}
	
	
}
