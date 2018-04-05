package pro.abacus.webproject.restclient;

public class Response {

	private Success success;
	private Contents contents;

	public Response() {

	}

	public Success getSuccess() {
		return success;
	}

	public void setSuccess(Success success) {
		this.success = success;
	}

	public Contents getContents() {
		return contents;
	}

	public void setContents(Contents contents) {
		this.contents = contents;
	}

	/* success or failure of API call */
	public boolean isSuccess() {
		if (success != null && success.getTotal() > 0) {
			return true;
		}
		return false;
	}

	/*
	 * return quote if collection is not empty otherwise null after call was
	 * successful
	 */
	public Quote getQuote() {
		if (isSuccess()) {
			if (contents != null && contents.getQuotes().size() > 0) {
				return contents.getQuotes().get(0);
			}
		}
		return null;
	}

}
