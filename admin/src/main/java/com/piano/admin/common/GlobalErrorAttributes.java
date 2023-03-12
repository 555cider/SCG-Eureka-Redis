package com.piano.admin.common;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest serverRequest, ErrorAttributeOptions options) {
	Map<String, Object> errorAttributes = super.getErrorAttributes(serverRequest, options);
	Throwable error = getError(serverRequest);
	if (error instanceof GlobalException) {
	    errorAttributes.put("code", ((GlobalException) error).getCode());
	    errorAttributes.put("message", error.getLocalizedMessage());
	    errorAttributes.remove("path");
	    errorAttributes.remove("error");
	}
	return errorAttributes;
    }

}