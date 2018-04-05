package pro.abacus.webproject.restclient;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pro.abacus.webproject.MvcConfiguration;
import pro.abacus.webproject.controllers.QuoteController;

@Service
public class QuoteServiceImpl implements QuoteService {
	
	final static Logger log = LoggerFactory.getLogger(QuoteServiceImpl.class);

	private final RestTemplate restTemplate;

	@Autowired
	public QuoteServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	

	@Override
	public Quote getDailyQuote(String category) {

		/*if the client pass the value - use it, if not - use inspirational */
		String quoteCategory = QuoteService.CATEGORY_INSPIRATIONAL;
		if (category != null && category.trim().length() > 0) {
			quoteCategory = category.trim();
		}

		/*POSTs data to a URL, returning an object mapped to the response body */
		Response response = this.restTemplate.getForObject("http://quotes.rest/qod.json?categorie={inspire}",
				            Response.class, quoteCategory);

		Quote quote = response.getQuote();
		log.info("Getting quote from rest service: " + quote);
		return quote;
	}

	@Override
	public String showQuote(Quote quote) {

		return "Quote of the day: " + quote.getQuote() + "\n" + "Author: " + quote.getAuthor();
	}


}
