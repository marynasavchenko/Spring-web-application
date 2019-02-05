package pro.abacus.webproject.restclient;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;


@RunWith(SpringRunner.class)
@RestClientTest(QuoteServiceImpl.class)
public class QuoteServiceImplTest {

	@Autowired
	private QuoteService quoteService;

	@Autowired
	private MockRestServiceServer server;
	
	@MockBean
	private Response response;
	
	@MockBean
	private Quote quote;
	
	
	private ClassPathResource getClassPathResource(String path) {
		return new ClassPathResource(path, getClass());
	}
	

	@Test
	public void callingGetQuoteAndExpectClientMakeCall() throws Exception {
		
		this.server.expect(requestTo("http://quotes.rest/qod.json?categorie=inspire"))
		.andRespond(withSuccess(getClassPathResource("quote.json"), MediaType.APPLICATION_JSON));
		
		Quote quote = this.quoteService.getDailyQuote("inspire");
		
		assertEquals(quote.getQuote(),
		"Do not worry if you have built your castles in the air. They are where they should be. Now put the foundations under them.");
		assertEquals(quote.getLength(),"122");
		assertEquals(quote.getAuthor(), "Henry David Thoreau");
		assertEquals(quote.getTitle(), "Inspiring Quote of the day");
		
		server.verify();
	}
	
	@Test
	public void sholdReturnDefaultQuoteWhenBadRequest() throws Exception {
		
		this.server.expect(requestTo("http://quotes.rest/qod.json?categorie=inspire"))
		.andRespond(MockRestResponseCreators.withNoContent());
		
		this.quoteService.getDailyQuote("inspire");
		
		server.verify();
	}
	
}
