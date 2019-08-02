package utils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.testng.annotations.DataProvider;

    @SuppressWarnings("deprecation")
    public class ExcelDataReader {

        @DataProvider(name = "ExcelDataReader")
        public static Object[][] ReadExcelData() throws FilloException {
            System.setProperty("ROW","1");
            System.setProperty("COLUMN","1");

            Fillo fillo = new Fillo();
            Connection conn = fillo.getConnection("src/testData/Movies.xlsx");

            String query = "Select * from data";

            Recordset recordset = conn.executeQuery(query);
            Object[] colNames = recordset.getFieldNames().toArray();
            int noOfColumns = colNames.length;
            int noOfRows = recordset.getCount();

            Object[][] data = new Object[noOfRows][noOfColumns];

            int row = 0;
            while(recordset.next())
            {
                for(int col=0;col<noOfColumns;col++)
                {
                    data[row][col]= recordset.getField(recordset.getField(col).name());
                }
                row = row+1;
            }
            return data;
        }

}
