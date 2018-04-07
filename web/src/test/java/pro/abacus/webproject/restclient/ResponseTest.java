package pro.abacus.webproject.restclient;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class ResponseTest {
	
	private SuccessResponse successResponse = mock(SuccessResponse.class);
	private ContentsResponse contentsResponse = mock(ContentsResponse.class);
	
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
	public void shouldReturnTrueWhenResponseIsSuccessful(){
		when(successResponse.getTotal()).thenReturn(1);
		
		Response response =new Response(successResponse, contentsResponse);
		assertTrue(response.isResponseSuccessful());
	}
	
	@Test
	public void shoulReturnFalseWhenResponseIsNotSuccessful(){
		when(successResponse.getTotal()).thenReturn(0);
		
		Response response =new Response(successResponse, contentsResponse);
		assertFalse(response.isResponseSuccessful());
	}

}
