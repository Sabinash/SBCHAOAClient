package com.sbchaoa_application.model;

public class LogIn {
	private String type;
	private String flatNbr;
	private String pswd;
	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getFlatNbr() {
		return flatNbr;
	}


	public void setFlatNbr(String flatNbr) {
		this.flatNbr = flatNbr;
	}


	public String getPswd() {
		return pswd;
	}


	public void setPswd(String pswd) {
		this.pswd = pswd;
	}


	@Override
	public String toString() {
		return "LogIn [type=" + type + ", flatNbr=" + flatNbr + ", pswd="
				+ pswd + "]";
	}
	
	

	
}
