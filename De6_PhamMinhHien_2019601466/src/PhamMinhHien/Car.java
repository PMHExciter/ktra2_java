package PhamMinhHien;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Car extends Product implements CarManager, Serializable {  
	private String color;
	private int max_speed;
	
	private List<Car> listCar; 
	
	public Car() {
		super();
		listCar = new ArrayList<>();
	}
	
	public Car(String product_id,String product_name, double product_price,int product_total, String color, int max_speed) {
		// khoi tao doi tuong cha
		super(product_id, product_name, product_price, product_total);
		// xac dinh du lieu doi tuong con
		this.color = color;
		this.max_speed = max_speed;
		listCar = new ArrayList<>();
	}
	
	public void setListCar(List<Car> list) {
		this.listCar = list;
	}
	
	public List<Car> getListCar() {
		return this.listCar;
	}
	
	//GET
	public String getColor() {
		return color;
	}

	public int getMax_speed() {
		return max_speed;
	}
	//SET
	public void setColor(String color) {
		this.color = color;
	}

	public void setMax_speed(int max_speed) {
		this.max_speed = max_speed;
	}	

	@Override
	public boolean addCar(Car c) {
		if(listCar == null) {
			listCar = new ArrayList<>();
		}
		boolean check_id = listCar.stream().noneMatch(std -> std.getProduct_id().equals(c.getProduct_id()));
		if(check_id) {
			listCar.add(c);
			return true;
		}
		return false;
	}

	@Override
	public boolean editCar(Car c) {
		if(listCar == null) {
			listCar = new ArrayList<>();
		}
		for(int i = 0; i < listCar.size(); i++) {
			if(listCar.get(i).getProduct_id().equals(c.getProduct_id())) {
				listCar.set(i, c);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delCar(Car c) {
		if(listCar == null) {
			listCar = new ArrayList<>();
		}
		for(int i = 0; i < listCar.size(); i++) {
			if(listCar.get(i).getProduct_id().equals(c.getProduct_id())) {
				listCar.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Car> searchCar(String name) {
		if(listCar == null) {
			listCar = new ArrayList<>();
		}
		List<Car> list = new ArrayList<>();
		for(Car car : listCar) {
			if(car.getProduct_name().equals(name)) {
				list.add(car);
			}
		}
		return list;
	}

	@Override
	public List<Car> sortedCar(double price) {
		Collections.sort(listCar, new Comparator<Car>() {

			@Override
			public int compare(Car o1, Car o2) {
				if(o1.getProduct_price() > o2.getProduct_price()) {
					return 1;
				} else if (o1.getProduct_price() < o2.getProduct_price()) {
					return -1;
				} else {
					return 0;
				}				
			}
			
		});
		return listCar;
	}	
}
