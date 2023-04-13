package cosc320project;

public class Abbreviation {
	
	String word;
	String meaning;
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public Abbreviation(String word, String meaning) {
		super();
		this.word = word;
		this.meaning = meaning;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "abrreviation: "+word+"\n"+"meaning: "+meaning;
	}
	
	
	

}
