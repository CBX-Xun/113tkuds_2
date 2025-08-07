import java.util.Scanner;

public class TicTacToeBoard {
    static char[][] board = new char[3][3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initBoard();
        char currentPlayer = 'X';
        while (true) {
            printBoard();
            System.out.println("玩家 " + currentPlayer + " 的回合，請輸入行與列 (0-2)：");
            int row = sc.nextInt(), col = sc.nextInt();

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                if (checkWin(currentPlayer)) {
                    printBoard();
                    System.out.println("🎉 玩家 " + currentPlayer + " 獲勝！");
                    break;
                }
                if (isDraw()) {
                    printBoard();
                    System.out.println("😐 平手！");
                    break;
                }
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("❌ 無效位置，請重新輸入。");
            }
        }
        sc.close();
    }

    static void initBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    static void printBoard() {
        System.out.println("---------");
        for (char[] row : board) {
            for (char c : row)
                System.out.print("|" + c);
            System.out.println("|");
            System.out.println("---------");
        }
    }

    static boolean isValidMove(int r, int c) {
        return r >= 0 && r < 3 && c >= 0 && c < 3 && board[r][c] == ' ';
    }

    static boolean checkWin(char p) {
        for (int i = 0; i < 3; i++)
            if (board[i][0] == p && board[i][1] == p && board[i][2] == p) return true;
        for (int j = 0; j < 3; j++)
            if (board[0][j] == p && board[1][j] == p && board[2][j] == p) return true;
        if (board[0][0] == p && board[1][1] == p && board[2][2] == p) return true;
        if (board[0][2] == p && board[1][1] == p && board[2][0] == p) return true;
        return false;
    }

    static boolean isDraw() {
        for (char[] row : board)
            for (char c : row)
                if (c == ' ') return false;
        return true;
    }
}
