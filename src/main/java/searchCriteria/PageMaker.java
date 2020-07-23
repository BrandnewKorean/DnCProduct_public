package searchCriteria;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	private int totalRow;
	private int startPageNo;
	private int endPageNo;
	private boolean prev;
	private boolean next;
	private int displayPageNo=3;
	private int lastPageNo;
	
	private Search search;
	
	public void setSearch(Search search) {
		this.search=search;
	}
	
	public void setTotalRow(int totalRow) {
		this.totalRow=totalRow;
		calcData();
	}
	
	public void calcData() {
		endPageNo=(int)(Math.ceil(search.getCurrentPage()/(double)displayPageNo)*displayPageNo);
		
		startPageNo=(endPageNo-displayPageNo)+1;
		
		lastPageNo=(int)Math.ceil(totalRow/(double)search.getPerPage());
		
		if(endPageNo>lastPageNo)endPageNo=lastPageNo;
		prev = startPageNo==1?false:true;
		next = endPageNo==lastPageNo?false:true;
	}
	
	public int getTotalRow() {
		return totalRow;
	}
	
	public int getstartPageNo() {
		return startPageNo;
	}
	
	public int getendPageNo() {
		return endPageNo;
	}
	
	public boolean isPrev() {
		return prev;
	}
	
	public boolean isNext() {
		return next;
	}
	
	public int getDisplayPageNo() {
		return displayPageNo;
	}
	
	public int getlastPageNo() {
		return lastPageNo;
	}
	
	public Search getSearch() {
		return search;
	}
	
	public String makeQuery(int currentPage) {
		UriComponents uriComponents =
			UriComponentsBuilder.newInstance()
			.queryParam("currentPage", currentPage)
			.queryParam("PerPage", search.getPerPage())
			.build();
		return uriComponents.toUriString();
	}
	
	public String makeSearch(int currentPage) {
		UriComponents uriComponents=
				UriComponentsBuilder.newInstance()
				.queryParam("currentPage",currentPage)
				.queryParam("perPage",search.getPerPage())
				.queryParam("searchType",search.getSearchType())
				.queryParam("keyword", search.getKeyword())
				.build();
		return uriComponents.toUriString();
	}

	@Override
	public String toString() {
		return "PageMaker [totalRow=" + totalRow + ", startPageNo=" + startPageNo + ", endPageNo=" + endPageNo
				+ ", prev=" + prev + ", next=" + next + ", displayPageNo=" + displayPageNo + ", lastPageNo="
				+ lastPageNo + ", criteria=" + search + "]";
	}//toString()
}//class
