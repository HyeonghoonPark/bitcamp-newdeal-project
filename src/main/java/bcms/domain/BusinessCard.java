package bcms.domain;

public class BusinessCard {

	private int mno;
	private int bcno;
	private int bcn;
	private String name;
	private String cname;
	private String cposi;
	private String caddr;
	private String mtel;
	private String tel;
	private String fax;
	private String email;
	private String homepage;
	private String memo;
	private int pcard;
	private String img;
	
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
	public int getBcn() {
		return bcn;
	}
	public void setBcn(int bcn) {
		this.bcn = bcn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCposi() {
		return cposi;
	}
	public void setCposi(String cposi) {
		this.cposi = cposi;
	}
	public String getCaddr() {
		return caddr;
	}
	public void setCaddr(String caddr) {
		this.caddr = caddr;
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
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getPcard() {
		return pcard;
	}
	public void setPcard(int pcard) {
		this.pcard = pcard;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	@Override
	public String toString() {
		return "BusinessCard [mno=" + mno + ", bcno=" + bcno + ", bcn=" + bcn + ", name=" + name + ", cname=" + cname
				+ ", cposi=" + cposi + ", caddr=" + caddr + ", mtel=" + mtel + ", tel=" + tel + ", fax=" + fax
				+ ", email=" + email + ", homepage=" + homepage + ", memo=" + memo + ", pcard=" + pcard + ", img=" + img
				+ "]";
	}

}
