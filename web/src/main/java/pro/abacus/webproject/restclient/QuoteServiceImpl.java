package pro.abacus.webproject.restclient;


import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteServiceImpl implements QuoteService {
	
	final static Logger log = LoggerFactory.getLogger(QuoteServiceImpl.class);
	
	private static final String REST_API_URL = "http://quotes.rest/qod.json?categorie={inspire}";
	

	private final RestTemplate restTemplate;

	@Autowired
	public QuoteServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	@Override
	public Quote getDailyQuote(String category) {

		trimCategory(category);
		Response response = this.restTemplate.getForObject(REST_API_URL, Response.class, category);
		Quote quote = response.getQuote();
		log.info("Getting quote from rest service: " + quote);
		return quote;
	}

	@Override
	public String showQuote(Quote quote) {

		return "Quote of the day: " + quote.getQuote() + "\n" + "Author: " + quote.getAuthor();
	}
	
	public void trimCategory(@NotNull String category){
		if (category.trim().length() > 0){
			category.trim();
		}
		
	}


}
