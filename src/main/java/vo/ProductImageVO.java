package vo;

public class ProductImageVO {
	private String productcode;
	private String filename;
	private boolean ismain;

	public boolean getIsmain() {
		return ismain;
	}
	public String getProductcode() {
		return productcode;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public void setIsmain(boolean ismain) {
		this.ismain = ismain;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	@Override
	public String toString() {
		return "ProductImageVO [productcode=" + productcode + ", filename=" + filename + ", ismain=" + ismain + "]";
	}
	
	
}
