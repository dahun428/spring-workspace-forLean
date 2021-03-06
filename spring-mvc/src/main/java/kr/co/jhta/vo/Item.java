package kr.co.jhta.vo;

public class Item {

	/*
	 * 
    create table spring_order_items(
        item_no number(10) primary key,
        order_no number(10) references spring_orders (order_no),
        product_no number(10) references spring_products(product_no),
        item_price number(10) not null,
        order_point number(10) not null,
        item_amount number(5) not null
    );
	 * 
	 * 
	 */
	
	private long no;
	private long price;
	private int amount;
	private long productNo;
	private long orderNo;
	
	
	
	public long getNo() {
		return no;
	}



	public void setNo(long no) {
		this.no = no;
	}



	public long getPrice() {
		return price;
	}



	public void setPrice(long price) {
		this.price = price;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public long getProductNo() {
		return productNo;
	}



	public void setProductNo(long productNo) {
		this.productNo = productNo;
	}



	public long getOrderNo() {
		return orderNo;
	}



	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}



	@Override
	public String toString() {
		return "Item [no=" + no + ", price=" + price + ", amount=" + amount + ", productNo=" + productNo + ", orderNo="
				+ orderNo + "]";
	}
	
	
	
}
