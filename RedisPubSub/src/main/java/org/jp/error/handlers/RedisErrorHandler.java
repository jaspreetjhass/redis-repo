package org.jp.error.handlers;

import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

import lombok.extern.java.Log;

@Service
@Log
public class RedisErrorHandler implements ErrorHandler {

	@Override
	public void handleError(Throwable throwable) {
		log.info("Error is : " + throwable.getMessage());
	}

}
