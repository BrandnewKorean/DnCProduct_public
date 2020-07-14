package vo;

public class ClientVO {
	private String id;
	private String password;
	private String name;
	private String birthday;
	private String email;
	private String address;
	private String profile;
	
	public String getId() {return id;}
	public String getPassword() {return password;}
	public String getName() {return name;}
	public String getBirthday() {return birthday;}
	public String getEmail() {return email;}
	public String getAddress() {return address;}
	public String getProfile() {return profile;}
	
	public void setId(String id) {this.id = id;}
	public void setPassword(String password) {this.password = password;}
	public void setName(String name) {this.name = name;}
	public void setBirthday(String birthday) {this.birthday = birthday;}
	public void setEmail(String email) {this.email = email;}
	public void setAddress(String address) {this.address = address;}
	public void setProfile(String profile) {this.profile = profile;}

	@Override
	public String toString() {
		return "ClientVO [id=" + id + ", password=" + password + ", name=" + name + ", birthday=" + birthday
				+ ", email=" + email + ", address=" + address + ", profile="+"]";
	}

} // UsersVO
