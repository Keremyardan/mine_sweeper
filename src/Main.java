import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner (System.in);

        System.out.println("Enter a value for A!");

        int row = input.nextInt();


        System.out.println("Enter a value for B!");
        int col = input.nextInt();


        if(row<2 || col<2) {
            System.out.println("Array can not be smaller than 2");
        }else {
            MineSweeper game = new MineSweeper(row, col);
        }
    }
}