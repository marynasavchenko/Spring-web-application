package pro.abacus.webproject.restclient;

public class SuccessResponse {

	private int total;

	public SuccessResponse() {
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "SuccessResponse [total=" + total + "]";
	}

}
