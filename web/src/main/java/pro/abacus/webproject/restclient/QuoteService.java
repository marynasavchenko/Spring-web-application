package pro.abacus.webproject.restclient;

public interface QuoteService {

	String CATEGORY_INSPIRATIONAL = "inspire";

	Quote getDailyQuote(String category);
	
	String showQuote(Quote quote);
}
