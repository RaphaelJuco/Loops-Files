import java.io.FileInputStream;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
/**
 * Write a description of class HW_Loops_Files here.
 *
 * @author (Raphael Juco)
 * @version (2/12/18)
 */
public class HW_Loops_Files
{
    public static void main(String[] args){
        int courseNumber; 
        double weightedAverage;
        int grade;
        Scanner inputFile = null;//null means nothing
        /*
         * This code block allows us to import data
         * from the file stated below, and manipulate it.
         */
        try
        {
            inputFile =
            new Scanner(new FileInputStream("courseData.txt"));

        }
        catch (IOException e)//Throws an exception if the file cannot be read
        {
            System.out.println("Error reading from this file");
            System.exit(0);
        }
        double programMultiplier = inputFile.nextDouble();
        double midtermMultiplier = inputFile.nextDouble();
        double finalMultiplier = inputFile.nextDouble();
        /*
         * Prints the table for each class, ID numbers, grades, 
         * weighted average, per student,
         * and a Pass or Fail programs grade.  The class
         * average is also printed.
         */ 
        while(inputFile.hasNextInt())//Works for multiple classes
        {
            //Reads class number and prints class number title and headings.
            courseNumber = inputFile.nextInt();
            tableHeadings(courseNumber);
            //Intialize the weight ratios and necessary variables
            double weightedGradePrograms;
            double weightedGradeMidterm;
            double weightedGradeFinal;
            double sumAverage = 0;
            int totalStudents = 0;
            /*
             * This creates the table for one class. Gets each student's
             * information and prints it. Computes each student's
             * weighted average and the sum average.
             */
            while (inputFile.hasNextInt())//Executes until an int can't be read
            {
                int input = inputFile.nextInt();
                if(input != 0){
                    int studentID = input;
                    totalStudents++;
                    int gradePrograms = inputFile.nextInt();
                    int gradeMidterm = inputFile.nextInt();
                    int gradeFinal = inputFile.nextInt();
                    weightedGradePrograms = gradePrograms * programMultiplier;
                    weightedGradeMidterm = gradeMidterm * midtermMultiplier;    
                    weightedGradeFinal = gradeFinal * finalMultiplier;    
                    weightedAverage = weightedGradePrograms + 
                    weightedGradeMidterm + weightedGradeFinal;
                    System.out.print(studentID + "       ");
                    System.out.print(gradePrograms + "       ");
                    System.out.print(gradeMidterm + "      ");
                    System.out.print(gradeFinal + "         ");
                    System.out.printf("%4.2f              ", weightedAverage);
                    passOrFail(gradePrograms);
                    sumAverage = sumAverage + weightedAverage;
                }
                else{//Breaks out of the loop
                    break;
                }
            }
            // compute and prints the class average
            classAverage(sumAverage, totalStudents);
        }
        //testVariables(finalMultiplier);
        inputFile.close();//Closes the file
    }

    /*
     * Enter any of the variable assigned to a value in the code up top. 
     * Example is shown below. Checks whether the value of any variable 
     * assigned above is the expected value.
     */
    public static void testVariables(double finalMultiplier){
        System.out.println(finalMultiplier);
    }      

    /*
     * Method for creating the headings of each class table stats
     */
    public static void tableHeadings(int courseNumber){
        System.out.println("Grade Data For Class " + courseNumber);
        System.out.println();
        System.out.print("ID   Programs   Midterm   Final   ");
        System.out.print("  Weighted Average   Programs grade");
        System.out.println();
    }       

    /*
     * Method for calculating the class average for each class
     */
    public static void classAverage(double sumAverage, int totalStudents){
        double classAverage = sumAverage / totalStudents;
        System.out.printf("Class Average: %4.2f", classAverage);
        System.out.println();
        System.out.println();
    }

    /*
     * Method for determining whether the grade passes or fails
     */
    public static void passOrFail(int gradePrograms){
        if(gradePrograms >= 70){
            System.out.println("Pass");
        }
        else{
            System.out.println("Fail");
        }
    }
}
//.......1.........2.........3.........4.........5.........6.........7.........8