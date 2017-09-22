package JavaSpringCoding.WriteToAndReadFromExcel;

import java.sql.SQLException;

public class AppTesting {

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {

		WriteToExcel wte = new WriteToExcel();

		wte.WriteNReadXecl();

	}

}
