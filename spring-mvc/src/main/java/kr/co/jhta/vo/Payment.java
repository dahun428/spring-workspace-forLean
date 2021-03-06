package kr.co.jhta.vo;

public class Payment {

	/*
	 *  
    create table spring_order_payments(
        pay_no number(10) primary key,
        pay_type varchar2(100) not null,
        pay_account varchar2(100) not null,
        order_no number(10) references spring_orders(order_no)

    );
	 * 
	 */
	private long no;
	private String type;
	private String account;
	private long orderNo;
	public Payment() {
		// TODO Auto-generated constructor stub
	}
	
	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "payment [no=" + no + ", type=" + type + ", account=" + account + ", orderNo=" + orderNo + "]";
	}
	

	
}


