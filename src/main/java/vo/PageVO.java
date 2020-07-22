package vo;

import java.util.List;

public class PageVO<T> {
	
	private List<T> list; // 조회결과 출력할 글 목록
	private int currentPage; // 출력할(요청받은) PageNo 
	private int perPage=10;  // 1페이지당 보여줄 Row(Record,튜플) 갯수
	private int totalCount; // 전체 row 갯수
	private int startno; // start Row No
	private int endno; // end Row No
	private int perPageNO=10; // 한 화면에 출력되는 PageNo 갯수 (paging2 에서 사용)
						  
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getStartno() {
		return startno;
	}
	public void setStartno(int startno) {
		this.startno = startno;
	}
	public int getEndno() {
		return endno;
	}
	public void setEndno(int endno) {
		this.endno = endno;
	}
	public int getPerPageNO() {
		return perPageNO;
	}
	public void setPerPageNO(int perPageNO) {
		this.perPageNO = perPageNO;
	}
	@Override
	public String toString() {
		return "PageVO [list=" + list + ", currentPage=" + currentPage + ", perPage=" + perPage + ", totalCount=" + totalCount
				+ ", startno=" + startno + ", endno=" + endno + ", perPageNO=" + perPageNO + "]";
	}
	
} // class
