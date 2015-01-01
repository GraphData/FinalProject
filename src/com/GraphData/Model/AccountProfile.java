package com.GraphData.Model;

public class AccountProfile extends AccountModel {
	private String birthday;
	private String college;
	private String major;
	private String hobby;
	
	public void setBirthday(String _birthday)
	{
		this.birthday = _birthday;
	}
	public String getBirthday()
	{
		return birthday;
	}
	public void setCollege(String _college)
	{
		this.college = _college;
	}
	public String getCollege()
	{
		return college;
	}
	public void setMajor(String _major)
	{
		this.major = _major;
	}
	public String getMajor()
	{
		return major;
	}
	public void setHobby(String _hobby)
	{
		this.hobby = _hobby;
	}
	public String getHobby()
	{
		return hobby;
	}
}
