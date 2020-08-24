package com.sample.web.form;

public class PaymentForm {

	private int id;
	private String userId;
	private int performanceId;
	private int pay;
	private String method;
	private String payMethodReal;
	private int usedPoint;
	
	public PaymentForm() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	
	public String getPayMethodReal() {
		return payMethodReal;
	}

	public void setPayMethodReal(String payMethodReal) {
		this.payMethodReal = payMethodReal;
	}

	public int getUsedPoint() {
		return usedPoint;
	}

	public void setUsedPoint(int usedPoint) {
		this.usedPoint = usedPoint;
	}

	public int getPerformanceId() {
		return performanceId;
	}

	public void setPerformanceId(int performanceId) {
		this.performanceId = performanceId;
	}

	@Override
	public String toString() {
		return "PaymentForm [id=" + id + ", userId=" + userId + ", performanceId=" + performanceId + ", pay=" + pay
				+ ", method=" + method + ", payMethodReal=" + payMethodReal + ", usedPoint=" + usedPoint + "]";
	}

	
	
}
