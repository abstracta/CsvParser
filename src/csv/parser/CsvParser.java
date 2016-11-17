/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Nicolas Darriulat
 * @github ndarriulat
 * 
 */
public class CsvParser {
    
    /**
     *
     * @param path Specifies the CSV file location.
     * @param ignoreFirstRow If true, ignores the first row of the CSV file.
     * @param ignoreFirstColumn If false, ignores the first row of the CSV file.
     * @param delimiter Specifies the String with which the CSV will be split. Must be null if no delimiter needed.
     * @return Returns a Object[][], which contains every row in the CSV file.
     * @throws FileNotFoundException If the CSV file is not found in the specified path, a FileNotFoundException will be thrown.
     * @throws csv.parser.DelimiterNotFoundException If one line of the file does not contain the given delimiter, a DelimiterNotFoundException will be thrown.
     */
    public static Object[][] generateObjectMatrixFromCsv(String path,boolean ignoreFirstRow,boolean ignoreFirstColumn,String delimiter) throws FileNotFoundException, DelimiterNotFoundException{
        Scanner scanner = new Scanner(new File(path));
        int lineNumber=1;
        if (!scanner.hasNext()) {
            return null;
        }
        if (delimiter!=null) {
            scanner.useDelimiter(delimiter);            
        }
        if (ignoreFirstRow) {
            String firstLine=scanner.nextLine();   
            if (delimiter!=null) {
                verifyLineContainsDelimiter(firstLine,delimiter,lineNumber);                
            }
        }        
        
        ArrayList<Object[]> linesList=new ArrayList<>();
        // Iterate the csv and add data to the list
        while(scanner.hasNext()){
            String line=scanner.nextLine();
            lineNumber++;
            
            // If a delimiter is provided, verify that the delimiter exists in the line.
            if (delimiter!=null) {
                verifyLineContainsDelimiter(line,delimiter,lineNumber);          
                String[] lineValues=line.split(delimiter);      

                if (ignoreFirstColumn) {
                    String[] newParameters=new String[lineValues.length-1];
                    System.arraycopy(lineValues, 1, newParameters,0,newParameters.length);
                    linesList.add(newParameters);                
                }
                else{
                    linesList.add(lineValues);
                }
            }
            else{
                Object[] lineValue=new Object[]{line};
                linesList.add(lineValue);
            }
        }
        scanner.close();
        
        if (linesList.size()<=0) {
            return null;
        }
        Object[][] matrix=convertListToMatrix(linesList);
        return matrix;
    }
    
    private static void verifyLineContainsDelimiter(String line,String delimiter,int lineNumber) throws DelimiterNotFoundException{
        if (!line.contains(delimiter)) {
                throw new DelimiterNotFoundException("The delimiter '"+delimiter+"' was not found at line "+lineNumber+" of the CSV file.");
        }
    }
    
    private static Object[][] convertListToMatrix(ArrayList<Object[]> list){
        Object[][] matrix=new Object[list.size()][list.get(0).length];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i]=list.get(i);
        }
        return matrix;
    }
}
