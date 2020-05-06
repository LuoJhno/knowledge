package java_exception_wrapper;

public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = -6072147852453980078L;
	private final int code;
	private final String message;
	private final Object object = null;

	public BadRequestException(int code, String message) {
		this.code = code;
		this.message = message;

	}

	public int getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public Object getObject() {
		return this.object;
	}
}
