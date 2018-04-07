package pro.abacus.webproject.restclient;

import org.springframework.util.Assert;

public class Response {

	private SuccessResponse success;
	private ContentsResponse contents;
	
	public Response() {
		
	}

	public Response(SuccessResponse success, ContentsResponse contents) {
		Assert.notNull(success, "Field success must not be null");
		Assert.notNull(contents, "Contens must not be null");
		this.contents = contents;
		this.success = success;
	}

	public SuccessResponse getSuccess() {
		return success;
	}

	public void setSuccess(SuccessResponse success) {
		this.success = success;
	}

	public ContentsResponse getContents() {
		return contents;
	}

	public void setContents(ContentsResponse contents) {
		this.contents = contents;
	}

	public boolean isResponseSuccessful() {
		return success.getTotal() > 0;	
	}

	/*
	 * return quote if collection is not empty otherwise null after call was
	 * successful
	 */
	public Quote getQuote() {
		if (isResponseSuccessful()) {
			if (contents != null && contents.getQuotes().size() > 0) {
				return contents.getQuotes().get(0);
			}
		}
		return null;
	}

}
