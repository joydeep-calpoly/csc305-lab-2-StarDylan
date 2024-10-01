package jsonorg;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.time.Year;

public class Person {
	private String name;
	private List<String> knownFor;
	private List<Award> awards;
	

	public static class Award {
		private String name;
		private int year;
		
		private Award(String name, int year) {
			this.name = name;
			this.year = year;
		}
		
		public static Award parseFromJson(JSONObject json) throws JSONException {
			String nameFromJson = json.getString("name");
			int yearFromJson = json.getInt("year");
			return new Award(nameFromJson, yearFromJson);
		}
		
		@Override
		public String toString() {
			return name + ", " + year;
		}
	}

	
	private Person(String name, List<String> knownFor, List<Award> awards) {
		this.name = name;
		this.knownFor = knownFor;
		this.awards = awards;
	}
	
	public static Person parseFromJson(JSONObject root) throws JSONException {
		String name = root.getString("name");
		
		List<String> knownForList = new ArrayList<>();
		JSONArray knownForJSON = root.getJSONArray("knownFor");
		for (int i = 0; i < knownForJSON.length(); i++) {
			knownForList.add(knownForJSON.getString(i));
		}
		
		List<Award> awardList = new ArrayList<>();
		JSONArray awardListJSON = root.getJSONArray("awards");
		for (int i = 0; i < awardListJSON.length(); i++) {
			awardList.add(Award.parseFromJson(awardListJSON.getJSONObject(i)));
		}
		
		return new Person(name, knownForList, awardList);
		
	}

	@Override
	public String toString() {
		return this.name + "\n\nKnown For:\n\t" + String.join("\n\t", this.knownFor) + "\n\n" + "Awards:\n\t" +
				String.join("\n\t", this.awards.stream().map(Award::toString).collect(Collectors.toList())) + "\n\n";
	}

	public String getName() {
		return name;
	}

	public List<String> getKnownFor() {
		return knownFor;
	}

	public List<Award> getAwards() {
		return awards;
	}
}
