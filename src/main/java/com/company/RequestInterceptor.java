package com.company;

import com.company.domain.error.ApiException;
import com.company.domain.error.RequestError;
import com.company.domain.Client;
import com.company.domain.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader(HttpHeaders.COMPANY_ACCESS_TOKEN);
        if (!StringUtils.isEmpty(accessToken) && Client.ACTIVE_CLIENT_SESSIONS.containsKey(accessToken)) {
            request.setAttribute(Client.AUTH_CLIENT_ID, Client.ACTIVE_CLIENT_SESSIONS.get(accessToken));
        } else {
            throw new ApiException(RequestError.E2);
        }
        return super.preHandle(request, response, handler);
    }
    
}
