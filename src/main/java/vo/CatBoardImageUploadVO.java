package vo;

public class CatBoardImageUploadVO {
	private int seq;
	private String uploadfile;
	
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(String uploadfile) {
		this.uploadfile = uploadfile;
	}
	
	@Override
	public String toString() {
		return "CatBoardImageUploadVO [seq=" + seq + ", uploadfile=" + uploadfile + "]";
	}
	
	

}
