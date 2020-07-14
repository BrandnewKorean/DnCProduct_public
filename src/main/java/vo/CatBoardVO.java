package vo;
public class CatBoardVO {
	private int seq;
	private String id;
	private String title;
	private String content;
	private String regdate;
	private int cnt;
	private int comments;
	
	public int getSeq() {return seq;}
	public String getId() {return id;}
	public String getTitle() {return title;}
	public String getContent() {return content;}
	public String getRegdate() {return regdate;}
	public int getCnt() {return cnt;}
	public int getComments() {return comments;}
	
	public void setSeq(int seq) {this.seq = seq;}
	public void setId(String id) {this.id = id;}
	public void setTitle(String title) {this.title = title;}
	public void setContent(String content) {this.content = content;}
	public void setRegdate(String regdate) {this.regdate = regdate;}
	public void setCnt(int cnt) {this.cnt = cnt;}
	public void setComments(int comments) {this.comments = comments;}
	
	@Override
	public String toString() {
		return "CatBoardVO [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", regdate="
				+ regdate + ", cnt=" + cnt + ", comments=" + comments + "]";
	}
} // CatBoardVO