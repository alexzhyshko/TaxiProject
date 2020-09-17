package main.java.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.connection.DBConnectionManager;
import application.context.annotation.Component;
import application.context.annotation.Inject;
import main.java.dto.Car;
import main.java.dto.Driver;

@Component
public class DriverRepository {

	@Inject
	private DBConnectionManager connectionManager;
	
	private Connection getNewConnection() {
		return this.connectionManager.getConnection();
	}
	
	public Driver getDriverByCar(Car car) {
		String query = "SELECT Drivers.id, Drivers.name, Drivers.surname, Drivers.rating FROM Drivers JOIN Driving ON Driving.driver_id = Drivers.id WHERE Driving.car_id=? AND Driving.dayOfDriving=CURDATE()";
		Connection connection = getNewConnection();
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, car.getId());
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					return Driver.builder()
							.id(rs.getInt(1))
							.name(rs.getString(2))
							.surname(rs.getString(3))
							.rating(rs.getFloat(4))
							.build();
				}
			}
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}