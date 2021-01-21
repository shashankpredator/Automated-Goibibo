package Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;


import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	 
	public static Object[][] getTableArray(String FilePath , String Sheet) throws Exception {   
   Object[][] tabArray = null;

   try {


	   ExcelWBook = new XSSFWorkbook(FilePath);

	   ExcelWSheet = ExcelWBook.getSheet(Sheet);
	   
	   int startRow = 1;

	   int startCol = 0;

	   int ci,cj;

	   int totalRows = ExcelWSheet.getLastRowNum();

	   int totalCols = ExcelWSheet.getRow(0).getLastCellNum();

	   tabArray=new String[totalRows][totalCols];

	   ci=0;

	   for (int i=startRow;i<=totalRows;i++, ci++) {           	   

		  cj=0;

		   for (int j=startCol;j<totalCols;j++, cj++){

			   tabArray[ci][cj]=getCellData(i,j);

			   System.out.println(tabArray[ci][cj]);  

				}

			}

		}

	catch (FileNotFoundException e){

		System.out.println("Could not read the Excel sheet");

		e.printStackTrace();

		}

	catch (IOException e){

		System.out.println("Could not read the Excel sheet");

		e.printStackTrace();

		}

	return(tabArray);

	}

public static Object getCellData(int RowNum, int ColNum) throws Exception {

	try{
       String CellData;
		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

		CellType dataType = Cell.getCellType();
        if  (dataType == CellType.NUMERIC) {

	
        	double CellData1 = Cell.getNumericCellValue();
		    int dataa = (int)CellData1;
			//CellData = Double.toString(CellData1);
			CellData = String.valueOf(dataa);
		    return CellData;

		}
		else{

		    CellData = Cell.getStringCellValue();
             
			return CellData;

		}
	}
		catch (Exception e){

		System.out.println(e.getMessage());

		throw (e);

		}

	}


}

