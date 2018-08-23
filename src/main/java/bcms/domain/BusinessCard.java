package bcms.domain;

public class BusinessCard {

	private int mno;
	private int bcno;
	private String name;
	private String mtel;
	private String tel;
	private String fax;
	private String email;
	private String memo;
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getBcno() {
		return bcno;
	}
	public void setBcno(int bcno) {
		this.bcno = bcno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMtel() {
		return mtel;
	}
	public void setMtel(String mtel) {
		this.mtel = mtel;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Override
	public String toString() {
		return "BusinessCard [mno=" + mno + ", bcno=" + bcno + ", name=" + name + ", mtel=" + mtel + ", tel=" + tel
				+ ", fax=" + fax + ", email=" + email + ", memo=" + memo + "]";
	}
	
	
}
