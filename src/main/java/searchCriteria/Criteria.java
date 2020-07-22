package searchCriteria;

public class Criteria {
	
	private int currentPage; // 출력할 page No
	private int perPage; // 1페이지당 보여줄 Row 갯수
	private int startno;
	private int endno;
	
	// 생성자로 초기화 하기
	public Criteria() {
		this.currentPage=1;
		this.perPage = 10;
	}
	
	// 1) 출력할 (요청받은) pageNo set
	public void setcurrentPage(int currentPage) {
		if(currentPage<1) currentPage=1;
		this.currentPage = currentPage;
	}
	
	// 2) 1page당 보여줄 row 확인
	public void setperPage(int perPage) {
		if(perPage<1 || perPage>50)
			perPage=10;
		this.perPage = perPage;
	}
	
	
	// 3) start,end row 계산
	public void setStartnoEndno() {
		if(startno<1) startno=1;
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
		return "Criteria [currentPage=" + currentPage + ", perPage=" + perPage + ", startno=" + startno + ", endno="
				+ endno + "]";
	}

} // class
