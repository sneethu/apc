package com.ingg.zuulservice.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.REQUEST_URI_KEY;

public class PostFilter /*extends ZuulFilter*/ {
 /*   @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getRequest().getRequestURI().matches("/meeting.*");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        Object originalRequestPath = context.getRequest().getRequestURI();
        String modifiedRequestPath = "/meeting-service" + originalRequestPath;

        context.put(REQUEST_URI_KEY, modifiedRequestPath);
        return null;
    }*/
}
