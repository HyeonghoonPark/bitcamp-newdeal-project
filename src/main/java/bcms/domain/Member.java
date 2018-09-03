package bcms.domain;

public class Member {
	
	private int mno;
	private String email;
	private String name;
	private String pwd;
	private String rid;
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	
	@Override
	public String toString() {
		return "Member [mno=" + mno + ", email=" + email + ", name=" + name + ", pwd=" + pwd + ", rid=" + rid + "]";
	}

}