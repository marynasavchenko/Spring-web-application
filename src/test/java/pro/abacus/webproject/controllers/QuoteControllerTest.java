package pro.abacus.webproject.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pro.abacus.webproject.restclient.Quote;
import pro.abacus.webproject.restclient.QuoteService;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(QuoteController.class)
@WithMockUser(roles = "USER")
public class QuoteControllerTest {

	private static final String QUOTE_CATEGORY = "inspire";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private QuoteService quoteService;

	@MockBean
	private Quote quote;

	@Test
	public void shouldShowWebServiceForm() throws Exception {
		mockMvc.perform(get("/webService"))
				.andExpect(status().isOk())
				.andExpect(view().name("webService"));
	}

	@Test
	public void shouldShowDailyQuote() throws Exception {
		when(quoteService.getDailyQuote(QUOTE_CATEGORY)).thenReturn(quote);
		when(quoteService.showQuote(quote)).thenReturn("quote of the day");

		mockMvc.perform(get("/quote"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("quote of the day")));
		verify(quoteService).showQuote(quote);
	}

	@Test
	public void shouldShowNotFound() throws Exception {
		when(quoteService.getDailyQuote(QUOTE_CATEGORY)).thenReturn(null);
		mockMvc.perform(get("/quote"))
				.andExpect(status().isNotFound());
	}
}
