package pro.abacus.webRestProject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
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
//change test
	    @Test
	    public void getVehicleDetailsWhenResultIsSuccessShouldReturnDetails()
	            throws Exception {
	        this.server.expect(requestTo("/greet/details"))
	                .andRespond(withSuccess("hello", MediaType.TEXT_PLAIN));
	        Quote quote = this.service.getDailyQuote("inspire");
	        assertThat(quote).isEqualTo("hello");
	    }

}
