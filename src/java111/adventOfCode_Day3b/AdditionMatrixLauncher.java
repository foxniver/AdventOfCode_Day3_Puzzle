package java111.adventOfCode_Day3b;
import java.util.*;
import java.io.*;
/**
* This class will contain the main method to launch the program that solves
* the puzzle 3b in Advent Of Code 2017.
*@author Stanislav Revin
*/
public class AdditionMatrixLauncher {
    
    
    /**
    * This is the main method of the program. It will do the following:
    * 1. Instantiate an object of AdditionMatrix Class;
    * 2. Call the method to run the program.
    *@param args The Command Line arguments.
    */
    public static void main(String[] args) {
        
        AdditionMatrix newAdditionMatrix = new AdditionMatrix();
        
        int numberToFind = 361527;
        newAdditionMatrix.runMatrix();
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        
        boolean willRun = true;
        int minimum = 0;
        
        try {
            
            numbers = newAdditionMatrix.findGreaterNumbers(numberToFind);
            
        
        } catch (IOException ex) {
            
            willRun = false;
            System.out.print("No numbers greater than " + numberToFind);
            System.out.println(" have been found.");
        
        }
        
        
        
        if (willRun) {
            
            minimum = newAdditionMatrix.findMinimumInArray(numbers);
            System.out.println(minimum);
            
        }
        
        
    }


}