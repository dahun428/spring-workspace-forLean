package kr.co.jhta.vo;

public class Delivery {
/*
 *  create table spring_order_delivery(
        delivery_no number(10) primary key,
        delivery_username varchar(100) not null,
        delivery_tel varchar2(30) not null,
        delivery_address varchar2(500) not null,
        delivery_status varchar2(50) default '물품준비중',
        order_no number(10) references spring_orders(order_no)
    );
 * 
 */
	private long no;
	private String username;
	private String tel;
	private String address;
	private String status;
	private long orderNo;
	public Delivery() {
		// TODO Auto-generated constructor stub
	}
	
	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "Delivery [no=" + no + ", username=" + username + ", tel=" + tel + ", address=" + address + ", status="
				+ status + ", orderNo=" + orderNo + "]";
	}
	
	
}
