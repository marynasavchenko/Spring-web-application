package pro.abacus.webproject.restclient;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;

@Service
public class QuoteServiceImpl implements QuoteService {

	final static Logger log = LoggerFactory.getLogger(QuoteServiceImpl.class);

	private static final String REST_API_URL = "http://quotes.rest/qod.json?categorie={inspire}";

	private final RestTemplate restTemplate;

	private Response response;

	private Quote quote;

	@Autowired
	public QuoteServiceImpl(RestTemplateBuilder restTemplateBuilder, Response response, Quote quote) {
		this.restTemplate = restTemplateBuilder.build();
		this.response = response;
		this.quote = quote;
	}

	@Override
	public Quote getDailyQuote(String category) {

		trimCategory(category);

		response = this.restTemplate.getForObject(REST_API_URL, Response.class, category);
		if (response != null) {
			quote = response.getQuoteObject();
			log.info("Getting quote from rest service: " + quote);
			return quote;
		}
		return quote.getDefaultQuote();
	}

	@Override
	public String showQuote(Quote quote) {
		return "Quote of the day: " + quote.getQuote() + "\n" + "Author: " + quote.getAuthor();
	}

	public void trimCategory(@NotNull String category) {
		if (category.trim().length() > 0) {
			category.trim();
		}
	}

}
