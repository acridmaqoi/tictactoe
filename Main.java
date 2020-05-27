package tictactoe;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final char[][] grid = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        printTable();
        while (!Utils.gameFinished(grid)) {
            addToken();
            printTable();
        }
    }

    public static void inputCells() {
        System.out.println("Enter cells:");
        String input = scanner.nextLine();

        int c = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (input.charAt(c) == '_') {
                    grid[i][j] = ' ';
                    c++;
                    continue;
                }

                grid[i][j] = input.charAt(c);
                c++;
            }
        }
    }

    public static void printTable() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] != 0 ? " " + grid[i][j] : "  ");
                if (j == 2) {
                    System.out.print(" ");
                }
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void addToken() {

        boolean failed = true;
        while (failed) {
            System.out.println("Enter the coordinates:");
            int[] chord = new int[2];
            for (int i = 0; i < 2; i++) {
                chord[i] = scanner.nextInt();
            }
            scanner.nextLine();

            if (!(chord[0] >= 1 && chord[0] <= 3 &&
                    chord[1] >= 1 && chord[1] <= 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            // swap row 1 and 3
            if (chord[1] == 1) {
                chord[1] += 2;
            } else if (chord[1] == 3) {
                chord[1] -= 2;
            }

            // swap indexes and -1
            int temp = chord[0];
            chord[0] = chord[1] - 1;
            chord[1] = temp - 1;

            // apply changes
            if (grid[chord[0]][chord[1]] == ' ' ||
                    grid[chord[0]][chord[1]] == 0) {
                grid[chord[0]][chord[1]] = currentPlayer;
                failed = false;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
        }

        currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
    }
}

