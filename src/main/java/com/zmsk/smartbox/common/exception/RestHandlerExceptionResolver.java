package com.zmsk.smartbox.common.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONException;

/**
 * 控制层异常统一处理类 如果controller中抛出异常，如果类型不是modelview，则转入此类中处理
 *
 */
public class RestHandlerExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(RestHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {

        logger.warn(ex.getClass().getSimpleName() + " when process rest request from host " + request.getRemoteHost()
                + " with url " + request.getPathInfo() + "?" + request.getQueryString());

        try {
            if (ex instanceof ZmskException && ((ZmskException) ex).getCode() >= 500) {
                logger.error(ex.getMessage(), ex);
            } else {
                logger.warn("REST request failed, {}", ex.getMessage());
            }

            if (ex instanceof ZmskException) {
                return handleZmskException((ZmskException) ex, request, response, handler);
            }

            if (ex instanceof NullPointerException) {
                return handleNullPointerException((NullPointerException) ex, request, response, handler);
            }

            if (ex instanceof IllegalArgumentException) {
                return handleIllegalArgumentException((IllegalArgumentException) ex, request, response, handler);
            }

            if (ex instanceof JSONException) {
                return handleJSONException((JSONException) ex, request, response, handler);
            }

            logger.error("exception when do exception resolver with path " + request.getPathInfo(), ex);

            return handleOtherException(ex, request, response, handler);

        } catch (Exception e) {
            logger.error("exception when do exception resolver with path {}", request.getPathInfo(), e);
            return null;
        }

    }

    protected ModelAndView handleZmskException(ZmskException ex, HttpServletRequest request,
                                               HttpServletResponse response, Object handler) throws IOException {
        HttpStatus status = HttpStatus.valueOf(ex.getCode());
        if (status == null) {
            logger.warn("invalid code for JumpPacketException with code {}", ex.getCode());
            status = HttpStatus.BAD_REQUEST;
        }
        return new RestModelAndView("", ex.getMessage(), ex.getClass().getSimpleName(), status, ex.getCode());
    }

    protected ModelAndView handleOtherException(Exception ex, HttpServletRequest request, HttpServletResponse response,
                                                Object handler) throws IOException {

        return new RestModelAndView("", ex.getMessage(), ex.getClass().getSimpleName(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ModelAndView handleNullPointerException(NullPointerException ex, HttpServletRequest request,
                                                      HttpServletResponse response, Object handler) throws IOException {

        return new RestModelAndView("", ex.getMessage(), ex.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
    }

    protected ModelAndView handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request,
                                                          HttpServletResponse response, Object handler) throws IOException {

        return new RestModelAndView("", ex.getMessage(), ex.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
    }

    protected ModelAndView handleJSONException(JSONException ex, HttpServletRequest request,
                                               HttpServletResponse response, Object handler) throws IOException {
        return new RestModelAndView("", ex.getMessage(), ex.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
    }
}
