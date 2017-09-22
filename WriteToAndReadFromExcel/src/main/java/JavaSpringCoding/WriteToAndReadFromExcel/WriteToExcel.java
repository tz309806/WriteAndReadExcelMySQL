package JavaSpringCoding.WriteToAndReadFromExcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteToExcel {

	private static final String	FILE_NAME	= "C:\\JAVAEXCELPRACTICE\\WriteToExcelPractice.xlsx";

	public void WriteNReadXecl(String[] args) {
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet("Data types in JAVA");
		MySqlConnectionUtil MySQL = new MySqlConnectionUtil();
		MySQL.MySqlconnector();
		Object[][] dataTypes = null;
		int rowNum = 0;
		System.out.println("Creating Excel now");

		for (Object[] dataType : dataTypes) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (Object field : dataType) {
				Cell cell = row.createCell(colNum++);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else {
					cell.setCellValue((Integer) field);
				}
			}
		}

		try {
			FileOutputStream outPutStream = new FileOutputStream(FILE_NAME);
			workBook.write(outPutStream);
			workBook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ----------Read Excel----------------------
		try {
			FileInputStream inputStream = new FileInputStream(FILE_NAME);
			Workbook readWorkBook = new XSSFWorkbook(inputStream);
			Sheet readSheet = workBook.getSheetAt(0);
			Iterator<Row> iterator = readSheet.iterator();

			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						System.out.println(currentCell.getStringCellValue() + "--");
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						System.out.println(currentCell.getNumericCellValue() + "--");

					}
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
