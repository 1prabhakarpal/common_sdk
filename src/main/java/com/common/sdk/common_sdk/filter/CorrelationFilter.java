package com.common.sdk.common_sdk.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.common.sdk.common_sdk.constants.ApplicationConstants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author PRABHAKAR PAL
 * @version 1.0
 * @since 2025-11-25
 * @implNote This class is used to add correlation id in the request and
 *           response.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorrelationFilter extends OncePerRequestFilter implements ApplicationConstants {

    /**
     * @implNote This method is used to add correlation id in the request and
     *           response.
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader(CORRELATION_ID);
        if (ObjectUtils.isEmpty(header)) {
            header = UUID.randomUUID().toString();
        }

        MDC.put(CORRELATION_ID, header);
        response.setHeader(CORRELATION_ID, header);
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(CORRELATION_ID);
        }
    }
}
