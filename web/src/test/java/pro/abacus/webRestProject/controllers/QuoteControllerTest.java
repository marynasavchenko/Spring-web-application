package pro.abacus.webRestProject.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import pro.abacus.webRestProject.restClient.Quote;
import pro.abacus.webRestProject.restClient.QuoteService;

@RunWith(SpringRunner.class)
@WebMvcTest(QuoteController.class)
@WithMockUser(roles = "USER")
public class QuoteControllerTest {
	
	private static final String QUOTE_CATEGORY = "inspire";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@MockBean
	private QuoteService quoteService;
	
	@MockBean
	private Quote quote;
	
	@Autowired
	private QuoteController quoteController = new QuoteController(quoteService);
	
	@Before
	public void setUp(){
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void shouldShowWebServiceForm() throws Exception {
		mockMvc.perform(get("/webService"))
		.andExpect(status().isOk())
		.andExpect(view().name("webService"));
	}
	
//test does not complete
	@Test
	public void shouldShowDailyQuote() throws Exception {
		
		MvcResult result =mockMvc.perform(get("/webService/quote"))
		.andExpect(status().isOk()).andReturn();
		assertNotNull(result);
		//String content = result.getResponse().getContentAsString();
		
		
		//quoteController.showDailyQuote();
		//verify(quoteService).getDailyQuote(QUOTE_CATEGORY);
		//assertNotNull(quote);
		
	}
	
	@Test
	public void whenQuoteNotFoundShouldReturnNotFound() throws Exception {
		
		when(quoteService.getDailyQuote(QUOTE_CATEGORY)).thenReturn(null);
		mockMvc.perform(get("/webService/quote"))
				.andExpect(status().isNotFound());
	}
	
}
