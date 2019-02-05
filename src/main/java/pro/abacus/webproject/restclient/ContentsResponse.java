package pro.abacus.webproject.restclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.util.Assert;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentsResponse {

	private ArrayList<Quote> quotes;

	public ContentsResponse() {

	}

	public ContentsResponse(ArrayList<Quote> quotes) {
		Assert.notNull(quotes, "Quotes must not be null");
		this.quotes = new ArrayList<Quote>(0);
	}

	public ArrayList<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(ArrayList<Quote> quotes) {
		this.quotes = quotes;
	}

}
