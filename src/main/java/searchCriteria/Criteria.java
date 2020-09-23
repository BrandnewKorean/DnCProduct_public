package searchCriteria;

public class Criteria {

	private int currentPage;
	private int perPage;
	private int startno;
	private int endno;
	
	public Criteria(){
		this.currentPage=1;
		this.perPage=10;
	}
	public void setCurrentPage(int currentPage) {
		if(currentPage<1) currentPage=1;
		this.currentPage=currentPage;
	}
	public void setPerPage(int perPage) {
		if(perPage<1||perPage>50) {
			perPage=10;
		}
		this.perPage = perPage;
	}
	public void setSnoEno() {
		if(startno<1)startno=1;
		startno=(currentPage-1)*perPage+1;
		endno=(startno+perPage)-1;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public int getPerPage() {
		return perPage;
	}
	public int getStartno() {
		return startno;
	}
	public int getEndno() {
		return endno;
	}
	@Override
	public String toString() {
		return "Criteria [currentPage=" + currentPage + ", perPage=" + perPage + ", startno=" 
				+ startno + ", endno="+ endno + "]";
	}
	
}
