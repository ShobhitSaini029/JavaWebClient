package com.nagarro.apiData;

import java.util.List;

public class NationalizeUserApiData {
	
	private int count;
    private String name;
    private List<Country> country;
    
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Country> getCountry() {
		return country;
	}
	public void setCountry(List<Country> country) {
		this.country = country;
	}
    
    public static class Country {
    	
    	private String country_id;
        private double counrtyProbability;
		public String getCountry_id() {
			return country_id;
		}
		public void setCountry_id(String country_id) {
			this.country_id = country_id;
		}
		public double getCounrtyProbability() {
			return counrtyProbability;
		}
		public void setCounrtyProbability(double counrtyProbability) {
			this.counrtyProbability = counrtyProbability;
		}
        
    	
    }

}
