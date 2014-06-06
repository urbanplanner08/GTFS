package DataProcess;

import java.io.*;
import java.util.*;

import jxl.*;
import jxl.read.biff.BiffException;
//read excel java class it is time consuming process; get DataGeneralization.csv  with all of the instance in one csv file

public class ReadExcel {
	public static final int WEEKDAY = 0;
	public static final int WEEKEND = 1;
	public static final int MORNING = 0;
	public static final int NOON = 1;
	public static final int AFTERNOON = 2;
	public static final int EVENING = 3;
	public static final int BAD = 1;
	public static final int FAIR = 2;
	public static final int GOOD = 3;
	public static final int[] DAY = { WEEKDAY, WEEKEND };
	public static final String[] DAY_STRING = { "weekday", "weekend" };
	public static final int[] TIME = { MORNING, NOON, AFTERNOON, EVENING };
	public static final int[] CLIMATE = { BAD, FAIR, GOOD };
	public static final int[] BUS_LINE_ID = { 9, 10, 11, 12, 63, 90 };
	public static final String PATH_PREFIX = "data\\";
	public static final int TIME_INDEX = 1;
	public static final int DATE_INDEX = 11;
	public static final int STOP_ID_INDEX = 3;
	public static final int ON_INDEX = 5;
	public static final int OFF_INDEX = 6;
	public static final int SUM_INDEX = 7;

	public static void main(String[] args) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(
				"DataGeneralization1.csv"));
		BufferedWriter writer2 = new BufferedWriter(new FileWriter(
				"address.csv"));
//		String resultLine = null;
//		String address=null;

		for (int day = 0; day < DAY_STRING.length; day++)
			for (int busIdIndex = 0; busIdIndex < BUS_LINE_ID.length; busIdIndex++) 
			{
				String preDirectory = PATH_PREFIX + "bus "
						+ BUS_LINE_ID[busIdIndex] + "\\"
						+ BUS_LINE_ID[busIdIndex] + " " + DAY_STRING[day];
				File file = new File(preDirectory);
				System.out.println(preDirectory);
				String[] fileList = file.list();
				for (int i = 0; i < fileList.length; i++) 
				{
					File dataFile = new File(preDirectory + "\\" + fileList[i]);
					System.out.println(preDirectory + "\\" + fileList[i]);
					Workbook book = Workbook.getWorkbook(dataFile);
					Sheet sheet = book.getSheet(0);
					String address = preDirectory + "\\" + fileList[i]+"\n";
					for (int rowIndex = 1; rowIndex < sheet.getRows(); rowIndex++) 
					{
						// System.out.println(sheet.getRows());
						if (sheet.getCell(TIME_INDEX, rowIndex).getContents() == null || 
								sheet.getCell(TIME_INDEX, rowIndex).getContents().equals(""))
							break;
						String busIndex = sheet.getCell(0, rowIndex)
								.getContents();
						String fullTime = sheet.getCell(1, rowIndex)
								.getContents();
						String direction = sheet.getCell(2, rowIndex)
								.getContents();
						String id = sheet.getCell(3, rowIndex).getContents();
						String stopName = sheet.getCell(4, rowIndex)
								.getContents();
						String passengerOn = sheet.getCell(5, rowIndex)
								.getContents();
						String passengerOff = sheet.getCell(6, rowIndex)
								.getContents();
						String passengerSum = sheet.getCell(7, rowIndex)
								.getContents();
						String weather = sheet.getCell(9, rowIndex)
								.getContents();
			
						String fullDate = sheet.getCell(11, rowIndex).getContents();
						if (id == null || id.equals(""))
							id = "12011";
						
						if (busIndex.contains("M")) {
							busIndex = BUS_LINE_ID[busIdIndex] + "";
							fullTime = sheet.getCell(0, rowIndex).getContents();
							direction = sheet.getCell(1, rowIndex)
									.getContents();
							id = sheet.getCell(2, rowIndex).getContents();
							stopName = sheet.getCell(3, rowIndex).getContents();
							passengerOn = sheet.getCell(4, rowIndex)
									.getContents();
							passengerOff = sheet.getCell(5, rowIndex)
									.getContents();
							passengerSum = sheet.getCell(6, rowIndex)
									.getContents();
							weather = sheet.getCell(8, rowIndex).getContents();
							fullDate = sheet.getCell(10, rowIndex)
									.getContents();
						}
						String resultLine = busIndex + "," + fullTime + ","
								+ direction + "," + id + "," + stopName + ","
								+ passengerOn + "," + passengerOff + ","
								+ passengerSum + "," + weather + "," + fullDate
								+ ","+	DAY_STRING[day]+"\n";
						writer.write(resultLine);
					}
					book.close();
					writer2.write(address);
				}

			}
		writer2.close();
		writer.close();

	}
}