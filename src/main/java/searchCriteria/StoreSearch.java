package searchCriteria;

public class StoreSearch extends Criteria{
	private String keyword;
	private String group1;
	private String group2;
	
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
		return "StoreSearch [keyword=" + keyword + ", group1=" + group1 + ", group2=" + group2 + "]";
	}
}
