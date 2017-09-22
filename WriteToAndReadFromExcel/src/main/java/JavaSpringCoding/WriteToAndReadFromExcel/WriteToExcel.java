package JavaSpringCoding.WriteToAndReadFromExcel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.jdbc.PreparedStatement;

public class WriteToExcel {

	final static Logger				logger		= Logger
																	.getLogger(WriteToExcel.class);

	private static final String	FILE_NAME	= "C:\\JAVAEXCELPRACTICE\\WriteToExcelPractice.xlsx";

	Connection							conn			= null;
	PreparedStatement					stmt			= null;
	Row									row			= null;
	Cell									cell			= null;

	public void WriteNReadXecl() throws ClassNotFoundException, SQLException {
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workBook.createSheet("Data types in JAVA");

		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306";
		final String USER = "root";
		final String PASS = "Tz64220866";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Connecting to database.");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			System.out.println("Creating Statement");
			//
			String sql;
			sql = "SELECT NAME, COUNTRYCODE, DISTRICT FROM WORLD.CITY";
			stmt = (PreparedStatement) conn.prepareStatement(sql);

			logger.info("Statement prepared.");

			// stmt.setInt(1, 6);

			ResultSet rs = stmt.executeQuery();

			System.out.println("Creating Excel now");

			Row headerRow = null;

			headerRow = spreadsheet.createRow((short) 0);

			cell = headerRow.createCell(0);
			cell.setCellValue("NAME");

			cell = headerRow.createCell(1);
			cell.setCellValue("COUNTRYCODE");

			cell = headerRow.createCell(2);
			cell.setCellValue("DISTRICT");

			int i = 1;
			while (rs.next()) {
				row = spreadsheet.createRow(i);
				cell = row.createCell(0);
				cell.setCellValue(rs.getString("NAME"));
				cell = row.createCell(1);
				cell.setCellValue(rs.getString("COUNTRYCODE"));
				cell = row.createCell(2);
				cell.setCellValue(rs.getString("DISTRICT"));
				i++;
			}

			logger.info("Result set generated.");

			// for (Object[] dataType : dataTypes) {
			// Row row = sheet.createRow(rowNum++);
			// int colNum = 0;
			// for (Object field : dataType) {
			// Cell cell = row.createCell(colNum++);
			// if (field instanceof String) {
			// cell.setCellValue((String) field);
			// } else {
			// cell.setCellValue((Integer) field);
			// }
			// }
			// }

			try {
				FileOutputStream outPutStream = new FileOutputStream(FILE_NAME);
				workBook.write(outPutStream);
				logger.info("Work book created");
				workBook.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// ----------Read Excel----------------------
			// try {
			// FileInputStream inputStream = new FileInputStream(FILE_NAME);
			// Workbook readWorkBook = new XSSFWorkbook(inputStream);
			// Sheet readSheet = workBook.getSheetAt(0);
			// Iterator<Row> iterator = readSheet.iterator();
			//
			// while (iterator.hasNext()) {
			// Row currentRow = iterator.next();
			// Iterator<Cell> cellIterator = currentRow.iterator();
			// while (cellIterator.hasNext()) {
			// Cell currentCell = cellIterator.next();
			// if (currentCell.getCellTypeEnum() == CellType.STRING) {
			// System.out
			// .println(currentCell.getStringCellValue() + "--");
			// } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
			// System.out.println(currentCell.getNumericCellValue()
			// + "--");
			//
			// }
			// }
			//
			// }
			//
			// } catch (FileNotFoundException e) {
			// e.printStackTrace();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		} finally {

		}
	}
}