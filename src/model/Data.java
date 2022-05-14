package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Data {
	
	private static final long serialVersionUID = 1L;
	public static ArrayList<Dane> data = new ArrayList<>();

	
	
	public static void loadUserData() {

		try {
			File file = new File("data/data.csv");
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Dane> userList = (ArrayList<Dane>) ois.readObject();
			Data.data = userList;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
