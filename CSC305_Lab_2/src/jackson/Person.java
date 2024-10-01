package jackson;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Year;

public class Person {
	@JsonProperty("name")
	private String name;

	@JsonProperty("knownFor")
	private List<String> knownFor;
	
	@JsonProperty("awards")
	private List<Award> awards;
	

	public static class Award {
		private String name;

		private int year;
		
		@JsonCreator
		private Award(@JsonProperty("name") String name, @JsonProperty("year") int year) {
			this.name = name;
			this.year = year;
		}
		
		@Override
		public String toString() {
			return name + ", " + year;
		}
	}

	@JsonCreator
	private Person(@JsonProperty("name") String name, @JsonProperty("knownFor") List<String> knownFor, @JsonProperty("awards") List<Award> awards) {
		this.name = name;
		this.knownFor = knownFor;
		this.awards = awards;
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
