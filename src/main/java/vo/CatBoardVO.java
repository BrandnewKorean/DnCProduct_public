package vo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class CatBoardVO {
	private int seq;
	private String id;
	private String title;
	private String content;
	private String regdate;
	private int cnt;
	private int comments;
	private int heart;
	public int getHeart() {
		return heart;
	}
	public void setHeart(int heart) {
		this.heart = heart;
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public void setRegdate(String regdate) throws ParseException {
		Date current = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date reg = fm.parse(regdate);
		long diff = current.getTime() - reg.getTime();
		long diffsec = (diff / 1000 % 60);
		long diffmin = (diff / (60 * 1000) % 60);
		long diffhour = (diff / (60 * 60 * 1000));
		long diffday = (diff / (24*60*60*1000));
		if(diffday <= 0) {
			if(diffhour <= 0) {
				if(diffmin <= 0) {
					if(diffsec < 30) {
						regdate = "방금";
					}else {
						regdate = diffsec+"초 전";
					}
				}else {
					regdate = diffmin+"분 전";
				}
			}else {
				regdate = diffhour+"시간 전";
			}
		}else {
			if(diffday > 0 && diffday < 7) {
				regdate = diffday+"일 전";
			}else {
				SimpleDateFormat fm2 = new SimpleDateFormat("yyyy/MM/dd");
				Date r = fm2.parse(regdate);
				String regd = fm2.format(r);
				regdate = regd;
			}
		}
		this.regdate = regdate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "CatBoardVO [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", regdate="
				+ regdate + ", cnt=" + cnt + ", comments=" + comments + ", heart=" + heart + "]";
	}
} // CatBoardVO