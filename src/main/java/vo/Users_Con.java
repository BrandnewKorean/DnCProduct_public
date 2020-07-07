package vo;

public class Users_Con {
	private String id;
	private String ad;
	private String loc;
	
	public String getId() {return id;}
	public String getAd() {return ad;}
	public String getLoc() {return loc;} 
	
	public void setId(String id) {this.id = id;}
	public void setAd(String ad) {this.ad = ad;}
	public void setLoc(String loc) {this.loc = loc;}

	@Override
	public String toString() {
		return "Users_Con [id=" + id + ", ad=" + ad + ", loc=" + loc + "]";
	}

} // Users_Con
