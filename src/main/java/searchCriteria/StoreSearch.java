package searchCriteria;

public class StoreSearch extends Criteria{
	private String keyword;
	private String group1;
	private String group2;
	private String order1;
	private String order2;
	
	public String getOrder1() {
		return order1;
	}

	public void setOrder1(String order1) {
		this.order1 = order1;
	}

	public String getOrder2() {
		return order2;
	}

	public void setOrder2(String order2) {
		this.order2 = order2;
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

	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword=keyword;
	}

	@Override
	public String toString() {
		return "StoreSearch [keyword=" + keyword + ", group1=" + group1 + ", group2=" + group2 + ", order1=" + order1
				+ ", order2=" + order2 + "]";
	}
}
