package vo;

public class CatBoardCommentVO {
	private int seq;
	private String id;
	private String content;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
	@Override
	public String toString() {
		return "CatBoardCommentVO [seq=" + seq + ", id=" + id + ", content=" + content + "]";
	}

}//CatBoardCommentVO
