# CsvParser

This is a simple library that parses CSV files, and then generates a Object matrix with the data it founds. 

It was developed to be used within DataProvider methods in TestNG, in order to initialize test cases with data stored in CSV files. However, it may be used with any purpose if you want to convert a CSV file into a matrix.

To use it, you should invoke generateObjectMatrixFromCsv method. 

*It receives the following parameters*

- path (String): indicates the path in which the CSV file is located.

- ignoreFirstRow (boolean): if its value is true, then the first row of the file is ignored.

- ignoreFirstColumn (boolean): if its value is true, then the first column of the file is ignored.

- delimiter (String): Specifies the String with which the CSV will be split. It must be null if no delimiter is needed.

*Exceptions that can be thrown*

- FileNotFoundException: If the CSV file is not found in the specified path, a FileNotFoundException will be thrown.

- csv.parser.DelimiterNotFoundException: If one line of the file does not contain the given delimiter, a DelimiterNotFoundException will be thrown.

*Return type*

It returns a Object[][], which contains every row in the CSV file.

