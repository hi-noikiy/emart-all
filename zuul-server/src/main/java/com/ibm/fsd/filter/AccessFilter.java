package com.ibm.fsd.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * All resource requests are prefiltered before routing
 */
public class AccessFilter extends ZuulFilter{
	
	// Logger
	private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);
	
	//@Autowired
	//private RestTemplate restTempate;

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}
	
	@Override
	public boolean shouldFilter() {
		RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        System.out.println(request.getRequestURI());
        if ("/user-api/api/login".equalsIgnoreCase(request.getRequestURI())) {
        	return false;
        }
		
        return true;
	}

	@Override
	public Object run() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		
		logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
		
		// get Authorization
    	final String authHeader = request.getHeader("Authorization");
    	
    	String token = null;
    	if (authHeader != null && authHeader.startsWith("Bearer ")) {
    		token = authHeader.substring(7);
        }
    	
    	if (StringUtils.isEmpty(token)) {
			logger.warn("illegal access without token");
			requestContext.setSendZuulResponse(false);
			requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
			requestContext.setResponseBody("illegal access");
			return null;
		} else {
			logger.info("Authorization token is ok");
			requestContext.setSendZuulResponse(true);
			requestContext.setResponseStatusCode(HttpStatus.OK.value());
			requestContext.setResponseBody("Success");
            requestContext.set("isSuccess", true);
		}
		return null;
	}
}
