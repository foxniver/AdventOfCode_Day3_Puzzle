package java111.adventOfCode_Day3b;
import java.util.*;
import java.io.*;
/**
* This is the AdditionMatrix Class. It will contain methods to consequently
* add the neigboring values in the 2-D matrix and display the sum in the next
* row of the sum in the upcoming cell of the matrix. It will solve the puzzle 3b
* provided in Advent Of Code 2017.
*@author Stanislav Revin
*/
public class AdditionMatrix {

    int minRow;
    int minColumn;
    int maxRow;
    int maxColumn;
    int value = 1;
    int number;
    int[][] matrix;
    boolean isOdd;
    
    /**
    * This method will obtain user's number.
    */
    public void getUserNumber() {
        
        Scanner sc = new Scanner(System.in);
        
        number = sc.nextInt();
        
    }
    
    /**
    * This method will set up dimensions for the array.
    */
    public void setArrayDimensions() {
    
        if (number % 2 == 0) {
        
            minRow = number / 2;
            maxRow = number / 2;
            
            minColumn = number / 2;
            maxColumn = number / 2;
            
            maxRow -= 1;
            maxColumn -= 1;
            
        } else if (number % 2 > 0) {
            
            minRow = number / 2;
            maxRow = number / 2;
            minColumn = number / 2;
            maxColumn = number / 2;
            
            minRow += 1;
            minColumn += 1;
            maxRow -= 1;
            maxColumn -= 1;
            
            isOdd = true;
            
        }
    
    }
    
    /**
    * This method will build a 2-D matrix and call methods to populate the
    * matrix with the sum of adjacent values. The first value in the matrix
    * will be 1, then the next cell will be populated with the sum of all
    * adjacent cells located up, down, left, right and diaganolly from the
    * new cell that will be filled.
    */
    public void runMatrix() {
        
        getUserNumber();
        setArrayDimensions();
        
        int upperLimit = number * number;
        int startingValue = 1;
        int sum = 0;
        
        matrix = new int[number][number];
        
       // Matrix spiral that travels from the center
        while (value <= upperLimit) {
            
            minRow -= 1;
            minColumn -= 1;
            maxRow += 1;
            maxColumn += 1;
        
            //1st step
            for (int index = maxRow - 1; index > minRow; index -= 1) {
                
                sum = sumUpValues(index, maxColumn);
                matrix[index][maxColumn] = sum;
                value += 1;
                
            }
            
            //2nd step
            for (int index = maxColumn; index > minColumn; index -= 1) {
                
                if (value == startingValue && !isOdd) {
                    
                    matrix[minRow][index] = startingValue;
                    value += 1;
                    
                } else {
                
                    sum = sumUpValues(minRow, index);
                    matrix[minRow][index] = sum;
                    value += 1;
                
                }
                
            }
            
            //3rd step
            for (int index = minRow; index < maxRow; index += 1) {
                
                sum = sumUpValues(index, minColumn);
                matrix[index][minColumn] = sum;
                value += 1;

            }
            
            //4th step
            for (int index = minColumn; index <= maxColumn; index += 1) {
                
                if (value == startingValue) {
                    matrix[maxRow][index] = startingValue;
                    value += 1;
                
                } else {
                    
                    sum = sumUpValues(maxRow, index);
                    matrix[maxRow][index] = sum;
                    value += 1;
                
                }

            }
            
        }
        
        //Display matrix
        displayMatrix();
    
    }
    
    /**
    * This method will display the matrix.
    */
    public void displayMatrix() {
    
        for (int row = 0; row < matrix.length; row += 1) {
            
            for (int col = 0; col < matrix[row].length; col += 1) {
                
                System.out.print(matrix[row][col] + "\t");
            
            }
            
            System.out.println();
        }
    
    }
    
    /**
    * This method will be scanning the adjacent values to see if anything
    * can be added. If it can be added, then the value will be added,
    * otherwise, in case of an exception, zero will be added instead.
    *@param row The integer that indicates the row number.
    *@param column The integer that indicate the column number.
    *@return sum The sum of the adjacent cells.
    */
    public int sumUpValues(int row, int column) {
        
        int sum = 0;
        
        try {
        
            sum += matrix[row][column - 1];
        
        } catch (ArrayIndexOutOfBoundsException ex) {
        
            sum += 0;
        
        }
        
        
        try {
        
            sum += matrix[row][column + 1];
        
        } catch (ArrayIndexOutOfBoundsException ex) {
            
            sum += 0;
        }
        
        try {
        
            sum += matrix[row - 1][column];
        
        } catch (ArrayIndexOutOfBoundsException ex) {
            
            sum += 0;
        }
        
        try {
        
            sum += matrix[row + 1][column];
        
        } catch (ArrayIndexOutOfBoundsException ex) {
            
            sum += 0;
        }
        
        try {
        
            sum += matrix[row - 1][column - 1];
        
        } catch (ArrayIndexOutOfBoundsException ex) {
            
            sum += 0;
        }
        
        try {
            
            sum += matrix[row - 1][column + 1];
        
        } catch (ArrayIndexOutOfBoundsException ex) {
            
            sum += 0;
        }
        
        try {
        
            sum += matrix[row + 1][column - 1];
            
        } catch (ArrayIndexOutOfBoundsException ex) {
            
            sum += 0;
        }
        
        try {
        
            sum += matrix[row + 1][column + 1];
        
        } catch (ArrayIndexOutOfBoundsException ex) {
            
            sum += 0;
        }
        
        return sum;
    
    }
    
    /**
    * This method will find a number in the matrix and produce the list
    * of numbers that are greater than the specified number or will throw
    * an exception if no values are added to the array.
    *@param number The number to be searched.
    *@return listToReturn The list of numbers.
    *@throws IOException The exception that will occur in case the resulting
    *       array is empty.
    */
    public ArrayList<Integer> findGreaterNumbers(int number) throws IOException {
        
        ArrayList<Integer> greaterNumbers = new ArrayList<Integer>();
        ArrayList<Integer> listToReturn = new ArrayList<Integer>();
        boolean isFound = false;
        
        for (int row = 0; row < matrix.length; row += 1) {
            
            for (int column = 0; column < matrix[row].length; column += 1) {
                
                if (number < matrix[row][column]) {
                    
                    greaterNumbers.add(matrix[row][column]);
                    
                }
                
            }
            
        }
        
        if (greaterNumbers.isEmpty()) {
            
            throw new IOException("IOException occured");
        
        }
        
        return greaterNumbers;
    
    }
    
    /**
    * This method will find the minimum value in the list of numbers.
    *@param numberList The list of numbers to sort.
    *@return minimum The minimum value.
    */
    public int findMinimumInArray(ArrayList<Integer> numberList) {
        
        int minimum = numberList.get(0);
        
        for (int number : numberList) {
            
            if (minimum > number) {
                
                minimum = number;
            
            }
        
        }
        
        return minimum;
    
    }

}
