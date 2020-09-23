package vo;

public class CatBoardCommentVO {
	private int seq;
	private String id;
	private String content;
	private int counter;
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
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	@Override
	public String toString() {
		return "CatBoardCommentVO [seq=" + seq + ", id=" + id + ", content=" + content + ", counter=" + counter + "]";
	}
	

}//CatBoardCommentVO
