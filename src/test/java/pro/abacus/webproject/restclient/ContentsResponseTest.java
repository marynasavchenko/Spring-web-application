package pro.abacus.webproject.restclient;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class ContentsResponseTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void whenQuotesAreNullShouldThrowIlligalArgumentException() {
		this.exception.expect(IllegalArgumentException.class);
		this.exception.expectMessage("Quotes must not be null");
		new ContentsResponse(null);
	}

	@Test
	public void shouldCreateContents() {
		ArrayList<Quote> quotes = new ArrayList<>();
		ContentsResponse contents = new ContentsResponse(quotes);

		assertNotNull(contents);
	}
}
