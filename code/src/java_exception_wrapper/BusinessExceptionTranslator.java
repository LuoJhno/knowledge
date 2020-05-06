package java_exception_wrapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class BusinessExceptionTranslator {

	@ExceptionHandler
	public ResponseEntity<MsgInfo<String>> handleBadRequestException(BusinessBadRequestException e) {
		return MsgInfo.code(e.getCode(), e.getMessage(), null);
	}

}
