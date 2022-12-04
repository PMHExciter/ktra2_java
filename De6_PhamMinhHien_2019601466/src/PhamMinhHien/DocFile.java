package PhamMinhHien;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class DocFile {
	public static List<Car> docFile(){
		List<Car> list = new ArrayList<>();
		try {
			FileInputStream fileInputStream = new FileInputStream("car.bin");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			list = (List<Car>) objectInputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void ghiFile(List<Car> list) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("car.bin");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
