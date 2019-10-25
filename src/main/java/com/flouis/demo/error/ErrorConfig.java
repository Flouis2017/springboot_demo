package com.flouis.demo.error;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorConfig implements ErrorPageRegistrar {

	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		ErrorPage err404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
		ErrorPage err500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
		registry.addErrorPages(err404Page, err500Page);
	}

}
