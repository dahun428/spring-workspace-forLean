package kr.co.jhta.form;

public class ProdctForm {

	private String catId;
	private String name;
	private long price;
	private long discountPrice;


	public ProdctForm() {
		// TODO Auto-generated constructor stub
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	public String getCatId() {
		return catId;
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

 

}
