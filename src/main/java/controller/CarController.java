package main.java.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import application.context.annotation.Component;
import application.context.annotation.Inject;
import application.context.annotation.Mapping;
import application.context.annotation.RestController;
import application.context.annotation.mapping.RequestType;
import main.java.dto.Car;
import main.java.service.CarService;

@Component
@RestController
public class CarController {

	private Gson gson = new Gson();
	
	@Inject
	CarService carService;
	
	@Mapping(route = "/car/getAll", requestType = RequestType.GET)
	public void getAllCars(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
		String userLocale = req.getHeader("User_Locale");
		List<Car> allCars = carService.getAllCars(userLocale);
		resp.setContentType("text/json");
		resp.getWriter().append(gson.toJson(allCars)).flush();
		resp.setStatus(200);
	}
	
}
