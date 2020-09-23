package vo;

public class CatBoardHeartVO {

	private int seq;
	private String id;
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
	@Override
	public String toString() {
		return "CatBoardHeartVO [seq=" + seq + ", id=" + id + "]";
	}
	
	
}
