package amz;

public class DesignTicTacToe {
    class TicTacToe {
        int N;
        int[] rows;
        int[] cols;
        int diagonal;
        int antiDiagonal;

        /**
         * Initialize your data structure here.
         */
        public TicTacToe(int n) {
            N = n;
            rows = new int[n];
            cols = new int[n];
            diagonal = 0;
            antiDiagonal = 0;
        }

        /**
         * Player {player} makes a move at ({row}, {col}).
         *
         * @param row    The row of the board.
         * @param col    The column of the board.
         * @param player The player, can be either 1 or 2.
         * @return The current winning condition, can be either:
         * 0: No one wins.
         * 1: Player 1 wins.
         * 2: Player 2 wins.
         */
        public int move(int row, int col, int player) {
            int place = player == 1 ? 1 : -1;
            rows[row] += place;
            if (Math.abs(rows[row]) == N) {
                return player;
            }
            cols[col] += place;
            if (Math.abs(cols[col]) == N) {
                return player;
            }
            if (row == col) {
                antiDiagonal += place;
                if (Math.abs(antiDiagonal) == N) {
                    return player;
                }
            }

            if (row == N - 1 - col) {
                diagonal += place;
                if (Math.abs(diagonal) == N) {
                    return player;
                }
            }
            return 0;
        }
    }

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
}
