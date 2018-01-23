package pro.abacus.webRestProject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import pro.abacus.webRestProject.restClient.*;

@RunWith(SpringRunner.class)
@RestClientTest(QuoteServiceImpl.class)
public class restClientTest {

	@Autowired
	private QuoteService service;

	@Autowired
	private MockRestServiceServer server;

	@Test
	public void callingGetQuoteAndExpectClientMakeCall() throws Exception {
		this.server.expect(requestTo("http://quotes.rest/qod.json?categorie=inspire"))
				.andRespond(withSuccess(getClassPathResource("quote.json"), MediaType.APPLICATION_JSON));

		Quote quote = this.service.getDailyQuote("inspire");
		assertThat(quote.getQuote()).isEqualTo(
				"Do not worry if you have built your castles in the air. They are where they should be. Now put the foundations under them.");
		assertThat(quote.getLength()).isEqualTo("122");
		assertThat(quote.getAuthor()).isEqualTo("Henry David Thoreau");
		assertThat(quote.getTitle()).isEqualTo("Inspiring Quote of the day");
	}

	private ClassPathResource getClassPathResource(String path) {
		return new ClassPathResource(path, getClass());
	}
}
