package pro.abacus.webproject.restclient;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contents {

	private ArrayList<Quote> quotes = new ArrayList<Quote>(0);

	public Contents() {

	}

	public ArrayList<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(ArrayList<Quote> quotes) {
		if (quotes == null) {
			this.quotes = new ArrayList<Quote>(0);
		}
		this.quotes = quotes;
	}

}
