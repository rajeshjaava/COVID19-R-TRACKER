package com.covid19.rtracker.models;

public class LocationStats {

	
	private String state;
	private String country;
	private String lat;
	private String lan;
	private Integer latestTotalCases;
	private Integer diffcases;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLan() {
		return lan;
	}
	public void setLan(String lan) {
		this.lan = lan;
	}
	public Integer getLatestTotalCases() {
		return latestTotalCases;
	}
	public void setLatestTotalCases(Integer latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
	public Integer getDiffcases() {
		return diffcases;
	}
	public void setDiffcases(Integer diffcases) {
		this.diffcases = diffcases;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", lat=" + lat + ", lan=" + lan
				+ ", latestTotalCases=" + latestTotalCases + ", diffcases=" + diffcases + "]";
	}
	
	
	
	
}
