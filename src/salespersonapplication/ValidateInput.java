package salespersonapplication;

import java.util.Scanner;

/**
 * SalespersonApplication (Week 3)
 * John D. Snurr
 * PRG/420
 * November 8, 2016
 * Manuael Davenport
 */

public class ValidateInput 
{    
    public double validateDouble(boolean allowNegativeValues)
    {
        Scanner sC = new Scanner(System.in); // New scanner for user input
        boolean isDouble = false, nextNumber = false; // Boolean data for conditional statements
        double inputToValidate = 0; // Declare and initialize double for scanner input
        
        do{ // do-while loop used to validate that the user inputs a number (specifically a double)
            
            if (nextNumber) // If the user is required to enter another number...
            {
                System.out.print("Enter a number: "); // Display this message
            }
                        
            if (sC.hasNextDouble()) // if-else control statement used to check scanner input without causing system crashes when reading strings
            {
                inputToValidate = sC.nextDouble(); // Assign the scanner double to the inputToValidate variable
                
                if(!allowNegativeValues) // If the program is checking for negative values, use this conditional statement as well
                {
                    isDouble = (inputToValidate >= 0); // Check for negative numbers after the double data type check has passed
                
                    if (!isDouble) // If the boolean check came back false...
                    {
                        System.out.println("No negative numbers please."); // Notify that the user can not use negative numbers
                        nextNumber = true; // Assign true to nextNumber to display input prompt to user (Line 22)
                        isDouble = false; // Assign false to isDouble to continue the loop
                        sC.nextLine(); // Clear the scanner
                    }
                }
            }
            else // If the input is not a number, inform the user, clear the scanner, and begin the loop again
            {
                System.out.println("That is not a valid number."); // Inform the user that the input is invalid
                isDouble = false; // Assign false to isDouble to continue the loop
                nextNumber = true; // Assign true to nextNumber to display input prompt to user (Line 22)
                sC.nextLine(); // Clear the scanner
            }
        } while (!isDouble); // Exit the loop when the isDouble boolean becomes true
        
        return inputToValidate; // Return the validated double to the program
    }
    
    public int validateInt(boolean allowNegativeValues)
    {
        Scanner sC = new Scanner(System.in); // New scanner for user input
        boolean isInt, nextNumber = false; // Boolean data for conditional statements
        int inputToValidate = 0; // Declare and initialize int for scanner input
        
        do{ // do-while loop used to validate that the user inputs a number (specifically an int)
            
            if (nextNumber) // If the user is required to enter another number...
            {
                System.out.print("Enter a number: "); // Display this message
            }
                        
            if (sC.hasNextInt()) // if-else control statement used to check scanner input without causing system crashes when reading strings
            {
                inputToValidate = sC.nextInt(); // Assign the scanner int to the inputToValidate variable
                isInt = (inputToValidate >= 0); // Check for negative numbers after the int data type check has passed
                
                if(!allowNegativeValues) // If the program is checking for negative values, use this conditional statement as well
                {
                    if (!isInt) // If the boolean check came back false...
                    {
                        System.out.println("No negative numbers please."); // Notify that the user can not use negative numbers
                        nextNumber = true; // Assign true to nextNumber to display input prompt to user (Line 22)
                        isInt = false; // Assign false to isInt to continue the loop
                        sC.nextLine(); // Clear the scanner
                    }
                }
            }
            else // If the input is not a number, inform the user, clear the scanner, and begin the loop again
            {
                System.out.println("That is not a valid number."); // Inform the user that the input is invalid
                isInt = false; // Assign false to isInt to continue the loop
                nextNumber = true; // Assign true to nextNumber to display input prompt to user (Line 22)
                sC.nextLine(); // Clear the scanner
            }
        } while (!isInt); // Exit the loop when the isInt boolean becomes true
        
        return inputToValidate; // Return the validated int to the program
    }
    
    public String validateString (String inputMessage, boolean allowNumbers)
    {
        Scanner sC = new Scanner(System.in); // New scanner for user input
        boolean checkString = true; // Boolean data for do-while leap
        String inputToValidate = null; // Declare and initialize String for scanner input
        
        do{ // do-while loop used to validate that the user input is text (with or without numbers)
           
            System.out.print(inputMessage); // inputMessage parameter prompts the user for the data to be entered
            
            if (sC.hasNext()) // if-esle control statement used to check scanner input
            {
                inputToValidate = sC.next(); // Assign the next scanner token to the inputToValidate variable
                checkString = inputToValidate.isEmpty(); // Check for an empty String
                
                ALLOW_NUMBERS: if (!allowNumbers) // If allowNumbers parameter is false run nest loops to check for numbers
                {
                    for (int i = 0; i < inputToValidate.length(); i++) // for loop to move through characters of inputToValidate String
                    {
                        for (char j = '0'; j <= '9'; j++) // for loop to move through '0' - '9' characters
                        {
                            if (inputToValidate.charAt(i) == j) // if statement to compare each character of inputToValidate to j
                            {
                                System.out.println("Please refrain from using numbers."); // Explain to user why the input is not accepted
                                checkString = true; // Set checkString to true to return to inputMessage
                                sC.nextLine(); // Clear the scanner
                                break ALLOW_NUMBERS; // break the to the do-while loop once first number is found
                            }
                        }
                    }
                }    
            }
        } while (checkString); // Exit the loop when the boolean becomes false
        
        return inputToValidate; // Return validated String to the program
    }
}

/**
 * Author: John D. Snurr
 * Date: 11/08/16
 */
