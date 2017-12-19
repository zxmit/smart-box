package com.zmsk.smartbox.controller.common.exception;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 封装rest服务异常，以json格式向外抛出异常
 *
 * @author warrior
 *
 */
public class RestModelAndView extends ModelAndView {

	public RestModelAndView(HttpStatus status) {
		JsonEntity entity = new JsonEntity();
		String msg = JSON.toJSONString(entity);
		RestView view = new RestView(status.value(), msg);
		this.setView(view);
	}

	/**
	 * 自定义的异常信息，状态吗需服务http规定的状态吗
	 *
	 * @param status
	 * @param errorMsg
	 */
	public RestModelAndView(int status, String errorMsg) {
		RestView view = new RestView(status, errorMsg);
		this.setView(view);
	}

	/**
	 * @param resource
	 *            资源(请求的参数)
	 * @param message
	 *            异常信息
	 * @param exception
	 *            异常类型
	 * @param status
	 */
	public RestModelAndView(String resource, String message, String exception, HttpStatus status) {
		this(resource, message, exception, status, null);
	}

	public RestModelAndView(String resource, String message, String exception, HttpStatus status, Integer detailCode) {
		JsonEntity entity = new JsonEntity(resource, message, exception, detailCode);
		String msg = JSON.toJSONString(entity);
		RestView view = new RestView(status != null ? status.value() : HttpStatus.SEE_OTHER.value(), msg);
		this.setView(view);
	}

	public RestModelAndView(View view) {
		super(view);
	}

	private class RestView implements View {

		private int status;
		private String message;

		public RestView(int status, String message) {
			this.status = status;
			this.message = message;
		}

		@Override
		public String getContentType() {
			return null;
		}

		@Override
		public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setStatus(getStatus());

			if (message == null) {
				message = "error!";
			}

			response.getWriter().write(message);

		}

		public int getStatus() {
			return status;
		}

	}

	@SuppressWarnings("unused")
	private class JsonEntity {

		private String resource = "";

		private String exception = "";

		private String message = "";

		/** 具体的错误码,如参数不合法是，http status code为400, 具体错误可能为40010 **/
		private Integer code;

		public JsonEntity() {

		}

		public JsonEntity(String resource, String message, String exception, Integer detailCode) {
			this.resource = resource;
			this.message = message;
			this.exception = exception;
			this.code = detailCode;
		}

		public String getRessource() {
			return resource;
		}

		public void setRessource(String ressource) {
			this.resource = ressource;
		}

		public String getException() {
			return exception;
		}

		public void setException(String exception) {
			this.exception = exception;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}
	}

}
