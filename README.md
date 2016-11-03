# CsvParser

This is a simple class that parses CSV files, and then generates a Object matrix with the data it founds. 

It was developed to be used within DataProvider methods in TestNG, in order to initialize test cases with data stored in CSV files.

To use it, you should invoke generateObjectMatrixFromCsv method. It receives three parameters:

- path (String): indicates the path in which the CSV file is located.

- ignoreFirstRow (boolean): if its value is true, then the first row of the file is ignored.

- ignoreFirstColumn (boolean): if its value is true, then the first column of the file is ignored.
