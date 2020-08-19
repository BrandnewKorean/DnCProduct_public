package vo;

public class CatStoreVO {
	private String productcode;
	private int quantity;
	private int price;
	private String regdate;
	private String group1;
	private String group2;
	private int seq;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getGroup1() {
		return group1;
	}
	public void setGroup1(String group1) {
		this.group1 = group1;
	}
	public String getGroup2() {
		return group2;
	}
	public void setGroup2(String group2) {
		this.group2 = group2;
	}
	@Override
	public String toString() {
		return "CatStoreVO [productcode=" + productcode + ", quantity=" + quantity + ", price=" + price + ", regdate="
				+ regdate + ", group1=" + group1 + ", group2=" + group2 + ", seq=" + seq + "]";
	}
}
