import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    String[][] bombMap;
    String[][] gameMap;

    int row;
    int col;
    int mineCount;
    int moveCount;

    int userRow;
    int userCol;
    int bombNumber;

    Scanner input = new Scanner(System.in);
    Random random = new Random();

    public MineSweeper(int row, int col) {
        this.row = row;
        this.col = col;
        this.bombMap = new String[row][col];
        this.gameMap = new String[row][col];
        this.mineCount = (row * col) / 4;
        this.moveCount = (col * row) - mineCount;
        mineMap();
        userMap();
    }

    public void mineMap() {
        System.out.println("Mine map!");
        for (int i = 0; i < bombMap.length; i++) {
            for (int j = 0; j < bombMap[i].length; j++) {
                bombMap[i][j] = " - ";
            }
        }

        for (int i = 0; i < this.mineCount; i++) {
            int randomRow = this.random.nextInt(this.row);
            int randomCol = this.random.nextInt(this.col);
            if (bombMap[randomRow][randomCol].equals(" * ")) {
                i--;
            } else {
                bombMap[randomRow][randomCol] = " * ";
            }

        }


        for (int i = 0; i < bombMap.length; i++) {
            for (int j = 0; j < bombMap[i].length; j++) {
                System.out.print(bombMap[i][j]);
            }
            System.out.println(" ");
        }
        System.out.println("-----------------------");
    }

    public void userMap() {
        System.out.println("Game map!");
        int controlRow = -1;
        int controlCol = -1;
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
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
            this.userRow = input.nextInt();
            System.out.println("Enter column");
            this.userCol = input.nextInt();
            if (userRow < this.row && userCol < this.col) {
                if (controlRow != userRow || controlCol != userCol) {
                    controlRow = userRow;
                    controlCol = userCol;

                    if (this.bombMap[userRow][userCol].equals(" * ")) {
                        System.out.println("Game Over!");
                        break;
                    }
                    mineControl();
                    if (this.gameMap[userRow][userCol].equals(" - ")) {
                        this.gameMap[userRow][userCol] = " " + String.valueOf(bombNumber) + " ";
                        moveCount--;
                        bombNumber = 0;
                    }
                    if (moveCount == 0) {
                        System.out.println("You Won!");
                        break;
                    }
                } else {
                    System.out.println("This coordinate has been used!");
                }
                for (int i = 0; i < gameMap.length; i++) {
                    for (int j = 0; j < gameMap[i].length; j++) {
                        System.out.print(gameMap[i][j]);
                    }
                    System.out.println();
                }
            } else {
                System.out.println("Your number can not be greater than your matrix!");
            }
        }
    }

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


