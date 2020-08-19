package vo;

public class ProductVO {
	private String productcode;
	private String name;
	private String productsize;
	private String brand;
	private int sales_quantity;
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductsize() {
		return productsize;
	}
	public void setProductsize(String productsize) {
		this.productsize = productsize;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getSales_quantity() {
		return sales_quantity;
	}
	public void setSales_quantity(int sales_quantity) {
		this.sales_quantity = sales_quantity;
	}
	@Override
	public String toString() {
		return "ProductVO [productcode=" + productcode + ", name=" + name + ", productsize=" + productsize + ", brand="
				+ brand + ", sales_quantity=" + sales_quantity + "]";
	}
}
