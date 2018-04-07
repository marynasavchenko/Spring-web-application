package pro.abacus.webproject.restclient;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentsResponse {

	private ArrayList<Quote> quotes = new ArrayList<Quote>(0);

	public ContentsResponse() {

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
