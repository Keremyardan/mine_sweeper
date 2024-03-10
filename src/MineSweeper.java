import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    // For bomb map and game map, two-dimensional arrays defined. (refers to second chapter)
    String[][] bombMap;
    String[][] gameMap;

    int row;
    int col;
    int mineCount;
    int moveCount;

    int userRow;
    int userCol;
    int bombNumber;

    // A scanner has indicated in otder to let the player add his/her own values in the creation of matrix. (refers to chapter 9)
    Scanner input = new Scanner(System.in);
    Random random = new Random();

    public MineSweeper(int row, int col) {
        this.row = row;
        this.col = col;
        this.bombMap = new String[row][col];
        this.gameMap = new String[row][col];
        // Mines are being placed as 4/1 according to the values those will be entered. (refers to chapter 5)
        this.mineCount = (row * col) / 4;
        this.moveCount = (col * row) - mineCount;
        mineMap();
        userMap();
    }

    //  Mine map has been declared which is being showed to the player. (refers to 6th chapter)
    public void mineMap() {
        System.out.println("Mine map!");
        for (int i = 0; i < bombMap.length; i++) {
            for (int j = 0; j < bombMap[i].length; j++) {
                // unfolded boxes are being showed as " - " (Refers to chapter 7)
                bombMap[i][j] = " - ";
            }
        }
        // In the code below, if the location is already mined, then i is getting a decrease in order to prevent extra mining process.
        for (int i = 0; i < this.mineCount; i++) {
            int randomRow = this.random.nextInt(this.row);
            int randomCol = this.random.nextInt(this.col);
            // Within the code combination below, mines are indicated as * (refers to the 8th chapter)
            if (bombMap[randomRow][randomCol].equals(" * ")) {
                i--;
            } else {
                bombMap[randomRow][randomCol] = " * ";
            }

        }

        //In the code below, the matrix values are being taken from player, getting combined and printed to the screenç.
        for (int i = 0; i < bombMap.length; i++) {
            for (int j = 0; j < bombMap[i].length; j++) {
                System.out.print(bombMap[i][j]);

            }
            System.out.println(" ");
        }
        System.out.println("-----------------------");
    }

    //  User map has been declared which is being showed to player as apart. (refers to 6th chapter)
    public void userMap() {
        System.out.println("Game map!");
        int controlRow = -1;
        int controlCol = -1;
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                // unfolded boxes are being showed as " - " (Refers to chapter 7)
                gameMap[i][j] = " - ";
            }
        }

        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                System.out.print(gameMap[i][j]);
            }

            System.out.println(" ");
        }
        while (true) {
            System.out.println("Enter row");
            //In the code below, the value for matrix is being asked from player to provide for rows. (refers to 9th chapter as well.)
            this.userRow = input.nextInt();
            System.out.println("Enter column");
            //In the code below, the value for matrix is being asked from player to provide for columns. (refers to 9th chapter as well.)
            this.userCol = input.nextInt();
            //In the code below, the previous moves of the player is being kept.
            if (userRow < this.row && this.userCol < this.col) {
                if (controlRow != userRow || controlCol != userCol) {
                    controlRow = userRow;
                    controlCol = userCol;
                    // Below, if player chooses a mined field, game finishes and "Game over" message is being thrown. (refers to 12th chapter)
                    if (this.bombMap[userRow][userCol].equals(" * ")) {
                        System.out.println("Game Over! Try Again!");
                        System.out.println();
                        mineMap();
                        userMap();
                        break;
                    }
                    // The function call below calls mineControl(); function to print the mines around the field that has chosen. (refers to 13th chapter).
                    mineControl();
                    if (this.gameMap[userRow][userCol].equals(" - ")) {
                        this.gameMap[userRow][userCol] = " " + String.valueOf(bombNumber) + " ";
                        moveCount--;
                        bombNumber = 0;
                    }
                    //in the block below, if the player wins, victory message is being printed on the screen as "You Won!" (refers to 14th chapter)
                    if (moveCount == 0) {
                        System.out.println("You Won! Another Game?");
                        System.out.println();
                        // The function calls below lets the player start over even if he/she wins or lose without running the code once again.
                        mineMap();
                        userMap();
                        break;
                    }
                } else {
                    //Below, if the coordinates have been used, a message is being thrown to the player in order to let him/her enter another value. (refers to 11th chapter)
                    System.out.println("This coordinate has been used!");
                }
                for (int i = 0; i < gameMap.length; i++) {
                    for (int j = 0; j < gameMap[i].length; j++) {
                        System.out.print(gameMap[i][j]);
                    }
                    System.out.println();
                }
            } else {
                // In the code below, if the values are inappropriate for creating a matrix, a message is being thrown to let the player know that this matrix is unable to create! (refers to 10th chapter)
                System.out.println("Your number can not be greater than your matrix!");
            }
        }
    }

    //In the code below and as declared above, near mine values are being controlled.
    public void mineControl() {
        for (int i = userRow - 1; i <= userRow + 1; i++) {
            for (int j = userCol - 1; j <= userCol + 1; j++) {
                if (i >= 0 && i < bombMap.length && j >= 0 && j < bombMap[i].length) {
                    if (i != userRow || j != userCol) {
                        if (bombMap[i][j].equals(" * ")) {
                            bombNumber++;
                        }
                    }
                }
            }
        }
    }

}

