package com.sbchaoa_application.model;

public class Owner {
	private String type;
	private String name;
	private String pswd;
	private String flatNbr;
	private String isAdmin;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getFlatNbr() {
		return flatNbr;
	}

	public void setFlatNbr(String flatNbr) {
		this.flatNbr = flatNbr;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "Owner [type=" + type + ", name=" + name + ", pswd=" + pswd
				+ ", flatNbr=" + flatNbr + ", isAdmin=" + isAdmin + "]";
	}
	

}
