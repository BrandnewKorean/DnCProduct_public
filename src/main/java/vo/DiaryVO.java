package vo;

public class DiaryVO {
	private String start;
	private String end;
	private String wdate;
	private String id;
	private String content;
	private String regdate;
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
	@Override
	public String toString() {
		return "DiaryVO [wdate=" + wdate + ", id=" + id + ", content=" + content + ", regdate=" + regdate + "]\n";
	}
}
