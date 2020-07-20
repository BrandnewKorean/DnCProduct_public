package vo;

public class DiaryUploadVO {
	private String wdate;
	private String id;
	private String filename;
	
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	@Override
	public String toString() {
		return "DiaryUploadVO [wdate=" + wdate + ", id=" + id + ", filename=" + filename + "]";
	}
}
