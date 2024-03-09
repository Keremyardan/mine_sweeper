import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        //For matrix dimensions,the customer is being asked to enter two values.

        Scanner input = new Scanner (System.in);

        System.out.println("Enter a value for A!");

        int row = input.nextInt();


        System.out.println("Enter a value for B!");
        int col = input.nextInt();

        // If the values are below 2, customer going to get a warning message about invalid data presentation. (refers to 4th chapter)
        if(row<2 || col<2) {
            System.out.println("Array can not be smaller than 2");
        }else {
            MineSweeper game = new MineSweeper(row, col);
        }
    }
}