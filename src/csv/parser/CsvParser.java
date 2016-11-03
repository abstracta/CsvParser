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
 * github username: ndarriulat
 * 
 */
public class CsvParser {
    
    /**
     *
     * @param path Specifies the CSV file location.
     * @param ignoreFirstRow If true, ignores the first row of the CSV file.
     * @param ignoreFirstColumn If false, ignores the first row of the CSV file.
     * @param delimiter Specifies the String with which the CSV will be split.
     * @return Returns a Object[][], which contains every row in the CSV file.
     * @throws FileNotFoundException If the CSV file is not found in the specified path, a FileNotFoundException will be thrown.
     */
    public static Object[][] generateObjectMatrixFromCsv(String path,boolean ignoreFirstRow,boolean ignoreFirstColumn,String delimiter) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(path));
        if (!scanner.hasNext()) {
            return null;
        }
        scanner.useDelimiter(delimiter);
        if (ignoreFirstRow) {
            scanner.nextLine();            
        }        
        
        ArrayList<Object[]> list=new ArrayList<>();
        // Iterate the csv and add data to the list
        while(scanner.hasNext()){
            String[] parameters=scanner.nextLine().split(delimiter);
            if (ignoreFirstColumn) {
                String[] newParameters=new String[parameters.length-1];
                System.arraycopy(parameters, 1, newParameters,0,newParameters.length);
                list.add(newParameters);                
            }
            else{
                list.add(parameters);
            }
        }
        scanner.close();
        
        if (list.size()<=0) {
            return null;
        }
        Object[][] matrix=convertListToMatrix(list);
        return matrix;
    }
    
    private static Object[][] convertListToMatrix(ArrayList<Object[]> list){
        Object[][] matrix=new Object[list.size()][list.get(0).length];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i]=list.get(i);
        }
        return matrix;
    }
}
