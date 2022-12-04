package PhamMinhHien;

import java.util.List;

public interface CarManager {
	public boolean addCar(Car c);
	public boolean editCar(Car c);
	public boolean delCar(Car c);
	public List<Car> searchCar(String name);
	public List<Car> sortedCar(double price);	
}
