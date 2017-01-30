package salespersonapplication; // Import salespersonapplication package

import java.util.Scanner; // Import java Scanner class

/**
 * SalespersonApplication (Week 3)
 * John D. Snurr
 * PRG/420
 * November 8, 2016
 * Manuael Davenport
 */

public class SalespersonApplication  // Declaration of SalespersonApplication class
{
    public static void main(String[] args) // Main method declaration
    {
        ValidateInput vI = new ValidateInput(); // Declare and initialize new ValidateInput reference
        Scanner sc = new Scanner(System.in); // Declare and initialize instance of Scanner
        
        System.out.print("Please enter the number of salespersons (at least 2) to record and compare: "); // Prompts the user to enter the number of salespersons they want to compare
        int salespersons; // int to hold number of salespersons input by user
        boolean minimumSalespersons = true; // boolean to determine whether or not to prompt user for correct input
        
        do{ // do-while loop to ensure that at least 2 salespersons are being compared
            if (!minimumSalespersons) // if the user didn't input a number great than or equal to 2...
            {
                System.out.print("Please enter a number greater than 2: "); // inform the user to input a number greater than 2
            }
            
            salespersons = vI.validateInt(false); // Assign a validated int to salespersons by calling the validateInt() method
            
            if (salespersons < 2) // if salespersons input is less than 2...
            {
                minimumSalespersons = false; // set minimumSalespersons to fales to prompt the message on Line 27
            }
            
        } while (salespersons < 2); // Calls the validateInt() method to validate user input
        
        SalespersonComparison salesComp = new SalespersonComparison(); // Declare and instantiate new SalespersonComparison reference
        salesComp.buildSalespersonArrays(salespersons); // Call the buildSalespersonArrays by passing the user input to determine array sizes
    }
}

class SalespersonComparison // Contains array creation, population, and comparison methods for salepesperson comparisons
{
    public void buildSalespersonArrays(int arrayLength) // Creates and populates arrays used in compareAndDisplay()
    {
        String[] firstNameArray = new String[arrayLength]; // Declare and initialize String array to hold first names using arrayLength parameter to determine size
        String[] lastNameArray = new String[arrayLength]; // Declare and initialize String array to hold last names using arrayLength parameter to determine size
        double[] salesArray = new double[arrayLength]; // Declare and initialize double array to hold annual sales using arrayLength parameter to determine size
        ValidateInput vI = new ValidateInput(); // Declare and instantiate new ValidateInput reference
        
        for (int i = 0; i < arrayLength; i++) // Iterate through length of arrays using for loop to populate arrays
        {
            String firstNameMessage = ("Please enter the salespersons first name: "); // Declare and initialize message to pass to validateString()
            firstNameArray[i] = vI.validateString(firstNameMessage, false); // Assign a validated String to firstNameArray at index i (no numbers accepted)
            
            String lastNameMessage = ("Please enter " + firstNameArray[i] + "'s last name: "); // Declare and initialize message to pass to validateString()
            lastNameArray[i] = vI.validateString(lastNameMessage, false); // Assign a validated String to lastNameArray at index i (no numbers accepted)
            
            System.out.print("Please enter " + firstNameArray[i] + " " + lastNameArray[i] + "'s total annual sales: "); // Prompt user to enter sales value for salesperson at index i
            salesArray[i] = vI.validateDouble(false); // Assign a validated double to salesArray at index i (no negative numbers or text allowed)
        }
        
        compareAndDisplay(firstNameArray, lastNameArray, salesArray); // Call the compareAndDisplay() method and pass the newly populated arrays for calculation
    }
    
    public void compareAndDisplay(String[] firstNameArray, String[] lastNameArray, double[] salesArray) // Takes the arrays from buildSalespersonArrays() and outputs sales data
    {
        CommissionCalculation cC = new CommissionCalculation(); // Declare and instaniate a reference to CommissionCalculation
        double highestSales = 0; // Declare and initialize highestSales to hold value while iterating through array
        
        for (double nextNumber : salesArray) // for-each loop to iterate through sales and find the highest sale value
        {
            if (nextNumber > highestSales) // Each time the number is higher than the current highestSales...
            {
                highestSales = nextNumber; // assign the new value to the highestSales variable
            }
        }
        
        System.out.printf("\nThe highest sales value this year is $%.2f!\n\n", highestSales); // Display the highestSales value to the user
        
        for (int i = 0; i < firstNameArray.length; i++) // for loop to iterate through all three arrays and display corresponding data
        {
            System.out.println("-------- " + firstNameArray[i] + " " + lastNameArray[i] + " --------"); // Print a separator for each salesperson
            
            if (salesArray[i] < highestSales) // If the current index of the salesArray is less than the highestSales value...
            {
                double salesDifference = highestSales - salesArray[i]; // calculate the difference...
                System.out.printf(firstNameArray[i] + " " + lastNameArray[i] + " needs to make $%.2f extra in sales to match the highest sales amount.\n", salesDifference); // and output the information
            }
            else // else if the data is equal (it can't be higher since the highestSales is the largest possible number)
            {
                System.out.println(firstNameArray[i] + " " + lastNameArray[i] + " reached the highest sales made this year!"); // Output that the salesperson has reached the highest value
            }
            
            cC.displayResults(salesArray[i], firstNameArray[i], lastNameArray[i]); // Call displayResults() method to calculate table for current array index
        }
    }
}

class CommissionCalculation // Declaration of CommissionCalculation class
{
    final double fixedSalary = 75_000; // Declare instanced fixedSalary variable
    double totalCommission = 0.00; // Declare and initialize instance totalCommission variable
    
    public void displayResults (double totalSalesInput, String firstName, String lastName) // Declare displayResults method with double totalSalesInput parameter
    {       
        // If-else-if statement to determine the totalCommission based off of the possible commission rates
	if (totalSalesInput >= 140_000) // If the totalSalesInput is greater than 140,000
	{
            totalCommission = totalSalesInput * (0.25 * 1.25);  // Then use the accelerated commission rate
	}
	else if (totalSalesInput >= (140_000 * .80) && totalSalesInput < 140_000) // Else-if the totalSalesInput is greater than 80% of 140,000 and less than 140,000
	{
            totalCommission = totalSalesInput * 0.25; // Then use standard commission rate
	}
		
	double totalIncome = totalCommission + fixedSalary; // Declare and initialize local totalIncome variable
        
        if (totalCommission != 0) // If the salesperson made commission through sales...
        {
            // then include it in the output message 
            System.out.printf(firstName + " " + lastName + " made an extra $%.2f through commissions and $%.2f total for the year!\n\n", totalCommission, totalIncome);
        }
        else // If the salesperson did not make any commission through sales...
        {
            // then exclude it in the output message
            System.out.printf(firstName + " " + lastName + " made no extra earnings through commissions and $%.2f total for the year!\n\n",  totalIncome);
        }
	
        // Inform the user of the tables contents and head the columns
        System.out.println("Below is a table listing the total compensation that could be made if " + firstName + " " + lastName + "'s\n"
                + "total sales increased by $5,000.00, up to 50% more of the current sales:\n");
        System.out.println("Total Sales\t Total Compensation"); // Use the tab escape sequence to create proper spacing for tabular data
        
        // For loop to create tabular sales data based on increments of 5,000 while the estimated sales is less than 50% of the totalSalesInput
	for (double i = totalSalesInput; i <= (totalSalesInput * 1.5); i += 5_000)
	{
            double tempCommission = 0; // Declare and initialize a temporary commission value during every iteration of the for loop
            double tempCommissionRate = 0.25; // Declare and initialize a tempory commission rate during every iteration of the for loop
			
            if (i >= 140_000) // If the estimated sales is greater than 140,000
            {
                tempCommissionRate *= 1.25; // Then multiply the temporaryCommissionRate by the acceleration factor and assign the new value
                tempCommission = i * tempCommissionRate; // Assign the tempCommission the new value based upon the incremental loop variable and the commission rate
            }
            else if (i >= (140_000 * .80) && i < 140_000) //Else-if the estimated sales is greater than 80% of 140,000 and less than 140,000
            {
                tempCommission = i * tempCommissionRate; // Then use the standare commission rate to assign the tempCommission the new value
            }
            
            double tempCompensation = tempCommission + fixedSalary; // Declare and initialize a tempCompensation value for the table
            int compLength = String.valueOf(i).length(); // Determine the length of the estimated total sales
            compLength -= 2; // Remove digits after the decimal point (not required, but easier to visualize)
            
            if (compLength <= 3) // If the value is less than 999
            {
                System.out.printf("$%.2f\t\t $%.2f\n", i, tempCompensation); // Use two tab escape sequences to space the data
            }
            else // If the value is greater than 999
            {
                System.out.printf("$%.2f\t $%.2f\n", i, tempCompensation); // Use one tab escape sequence to space the data
            }
	}
        
        System.out.println(""); // Print a blank line to make the output cleaner
    }
}

/**
 * Author: John D. Snurr
 * Date: 10/31/16
 */