package application.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import application.context.ApplicationContext;
import application.entity.ResponseEntity;
import application.routing.Router;
import application.utils.HttpUtils;

public class BoilerplateController extends HttpServlet {

	private static Router router = (Router) ApplicationContext.getSingletonComponent(Router.class);

	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		try {
			Optional<ResponseEntity<Object>> responseObject = router.routeGet(req, resp);
			if(responseObject.isEmpty()) {
				return;
			}
			prepareResponse(responseObject.get(), resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		try {
			Optional<ResponseEntity<Object>> responseObject = router.routePost(req, resp);
			if(responseObject.isEmpty()) {
				return;
			}
			prepareResponse(responseObject.get(), resp);
		} catch (IOException e) {
			// TODO implement logging
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		try {
			Optional<ResponseEntity<Object>> responseObject = router.routePut(req, resp);
			if(responseObject.isEmpty()) {
				return;
			}
			prepareResponse(responseObject.get(), resp);
		} catch (IOException e) {
			// TODO implement logging
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		try {
			Optional<ResponseEntity<Object>> responseObject = router.routeDelete(req, resp);
			if(responseObject.isEmpty()) {
				return;
			}
			prepareResponse(responseObject.get(), resp);
		} catch (IOException e) {
			// TODO implement logging
			e.printStackTrace();
		}
	}
	

	private void prepareResponse(ResponseEntity<Object> responseEntity, HttpServletResponse resp) {
		HttpUtils.setResponseBody(resp, responseEntity.getResponsePayload(), responseEntity.getContentType(), responseEntity.getStatusCode(), responseEntity.getCharset());
	}

}
