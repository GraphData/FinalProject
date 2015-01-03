package com.GraphData.Model;

public class Newsfeed {
	private String content;
	private String time;
	private String publisher;
	public void setContent(String _content)
	{
		this.content = _content;
	}
	public String getContent()
	{
		return content;
	}
	public void setTime(String _time)
	{
		this.time = _time;
	}
	public String getTime()
	{
		return time;
	}
	public void setPublisher(String _publisher)
	{
		this.publisher = _publisher;
	}
	public String getPublisher()
	{
		return publisher;
	}
}
