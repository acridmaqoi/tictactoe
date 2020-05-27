package tictactoe;

public class Utils {

    private Utils() {
    }

    // checks to see if there is three in a row in a straight line (across or vertically)
    public static Character findStraight(char[][] grid) {
        for (int i = 0; i < 3; i++) { // row / column
            int d = 1, a = 1;
            for (int j = 1; j < 3; j++) { // offset
                if (grid[i][j] != 0) {
                    if (grid[i][j] == grid[i][0]) {
                        // vertically/down
                        d++;
                    }
                }
                if (grid[j][i] != 0) {
                    if (grid[j][i] == grid[0][i]) {
                        // across
                        a++;
                    }
                }
            }

            if (d == 3) {
                return grid[i][0];
            } else if (a == 3) {
                return grid[0][i];
            }
        }

        return null;
    }

    // checks to see if there is three in a row in either diagonal direction
    public static Character findDiag(char[][] grid) {
        int da = 1, db = 1, t = 1; // counter for diagonal A (0,0), counter for diagonal B (0,2), index iterator for diagonal B
        for (int o = 1; o < 3; o++) {
            if (grid[0][0] != 0) {
                // checking from point 0,0
                if (grid[o][o] == grid[0][0]) {
                    da++;
                }
            }
            if (grid[0][2] != 0) {
                // checking from point 0,2
                if (grid[o][t] == grid[0][2]) {
                    db++;
                }
                t--;
            }

        }

        if (da == 3) {
            return grid[0][0];
        } else if (db == 3) {
            return grid[0][2];
        }

        return null;
    }

    public static boolean gameFinished(char[][] grid) {
        if (findStraight(grid) != null) {
            System.out.println(findStraight(grid) + " wins");
            return true;
        }
        if (Utils.findDiag(grid) != null) {
            System.out.println(Utils.findDiag(grid) + " wins");
            return true;
        }

        // if grid is full
        int u = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'X' || grid[i][j] == 'O') {
                    u++;
                }
            }
        }

        if (u == 9) {
            System.out.println("Draw");
            return true;
        }

        return false;
    }

    // returns false if the difference between 'X' and 'O' is 2 or more
    public static boolean equalXO(char[][] grid) {
        int x = 0, o = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'X')
                    x++;
                else if (grid[i][j] == 'O')
                    o++;
            }
        }

        return (x <= o || x - o < 2) && (o <= x || o - x < 2);
    }
}
