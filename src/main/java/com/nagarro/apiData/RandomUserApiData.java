package com.nagarro.apiData;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RandomUserApiData {

	private List<Result> results;

	public List<Result> getResults() {
		return results;
	}


	public void setResults(List<Result> results) {
		this.results = results;
	}


	@NoArgsConstructor
	@AllArgsConstructor
	@Data
    public static class Result {
		private String gender;
        private Name name;
        private Dob dob;
        private String nat;
        public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public Name getName() {
			return name;
		}
		public void setName(Name name) {
			this.name = name;
		}
		public Dob getDob() {
			return dob;
		}
		public void setDob(Dob dob) {
			this.dob = dob;
		}
		public String getNat() {
			return nat;
		}
		public void setNat(String nat) {
			this.nat = nat;
		}
		
}


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Name {
    	
    	private String title;
        private String first;
        private String last;
        public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getFirst() {
			return first;
		}
		public void setFirst(String first) {
			this.first = first;
		}
		public String getLast() {
			return last;
		}
		public void setLast(String last) {
			this.last = last;
		}
		
    }
    

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Dob{
    	private LocalDateTime date;
    	private int age;
    	
		public LocalDateTime getDate() {
			return date;
		}
		public void setDate(LocalDateTime date) {
			this.date = date;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
    }
}