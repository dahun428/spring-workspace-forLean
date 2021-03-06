package kr.co.jhta.vo;

import java.util.Date;

public class Order {
/**
 * 
create table spring_orders(
    order_no number(10) primary key,
    order_username varchar2(100) not null,
    order_tel varchar2(20) not null,
    order_status varchar2(100) default '결제완료',
    order_created_date date default sysdate
    );
    
 * 
 */
	
	private long no;
	private String username;
	private String tel;
	private String status;
	private Date createdDate;
	
	public Order() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Order [no=" + no + ", username=" + username + ", tel=" + tel + ", status=" + status + ", createdDate="
				+ createdDate + "]";
	}
	
	
}
