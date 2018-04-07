package pro.abacus.webproject.restclient;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class ResponseTest {
	
	private SuccessResponse successResponse = mock(SuccessResponse.class);
	private ContentsResponse contentsResponse = mock(ContentsResponse.class);
	private Quote quote = mock(Quote.class);
	private ArrayList<Quote> quotesFromRestApi = new ArrayList<>();
	private static final String DEFAULT_QUOTE_AUTHOR ="Albert Einstein";
	private static final String DEFAULT_QUOTE ="Once you stop learning, you start dying";
	
	@Rule 
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void whenSuccessIsNullShouldThrowIlligalArgumentException(){
		this.exception.expect(IllegalArgumentException.class);
		this.exception.expectMessage("Field success must not be null");
		new Response(null, null);
	}
	
	@Test
	public void whenContensIsNullShouldThrowIlligalArgumentException(){
		this.exception.expect(IllegalArgumentException.class);
		this.exception.expectMessage("Contens must not be null");
		new Response(successResponse, null);
	}
	
	@Test
	public void shouldCreateResponse(){
		Response response =new Response(successResponse, contentsResponse);
		assertNotNull(response);
	}
	
	@Test 
	public void whenResponseIsSuccessfulShouldReturnTre(){
		when(successResponse.getTotal()).thenReturn(1);
		
		Response response =new Response(successResponse, contentsResponse);
		assertTrue(response.isResponseSuccessful());
	}
	
	@Test
	public void whenResponseIsNotSuccessfulShoulReturnFalse(){
		when(successResponse.getTotal()).thenReturn(0);
		
		Response response =new Response(successResponse, contentsResponse);
		assertFalse(response.isResponseSuccessful());
	}
	
	@Test
	public void whenCallWasSuccessfulShouldReturnQuote(){
		Response response =new Response(successResponse, contentsResponse);
		quotesFromRestApi.add(quote);
		when(contentsResponse.getQuotes()).thenReturn(quotesFromRestApi);
		when(successResponse.getTotal()).thenReturn(1);
		
		assertEquals(quote,response.getQuote());
	}
	
	@Test
	public void whenCallWasUnsuccessfulShouldReturnDefaultQuote(){
		Response response =new Response(successResponse, contentsResponse);
		when(successResponse.getTotal()).thenReturn(0);
		
		assertNotNull(response.getDefaultQuote());
		assertEquals(DEFAULT_QUOTE_AUTHOR, response.getDefaultQuote().getAuthor());
		assertEquals(DEFAULT_QUOTE, response.getDefaultQuote().getQuote());
	}

}
