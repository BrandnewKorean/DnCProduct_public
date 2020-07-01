package vo;

public class CatComment {
	private int seq;
	private String id;
	private String content; 
	
	public int getSeq() {return seq;}
	public String getId() {return id;}
	public String getContent() {return content;}
	
	public void setSeq(int seq) {this.seq = seq;}
	public void setId(String id) {this.id = id;}
	public void setContent(String content) {this.content = content;}

	@Override
	public String toString() {
		return "CatComment [seq=" + seq + ", id=" + id + ", content=" + content + "]";
	}
	
} // CatComment
