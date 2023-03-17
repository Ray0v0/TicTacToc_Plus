import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static Player[] players = new Player[2];
    private static int nowPlayer = 0;
    public static void changeNowPlayer() {
        nowPlayer = 1 - nowPlayer;
    }
    public static Player findPlayer(int identity) {
        Player ret = null;
        if (identity == -1) {
            ret = players[0];
        } else if (identity == 1) {
            ret = players[1];
        }
        return ret;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please input the size of chessboard [3, 9]: ");
        Board chessBoard = new Board(in.nextInt());

        // player1, 储存在players[0]中, identity为-1
        System.out.print("Please choose player1, human(h) or computer(c)? Default is human :");
        if (Objects.equals(in.next(), "c")) {
            players[0] = new ComputerPlayer(-1);
        } else {
            players[0] = new HumanPlayer(-1);
        }

        // player2, 储存在players[1]中, identity为 1
        System.out.print("Please choose player2, human(h) or computer(c)? Default is human :");
        if (Objects.equals(in.next(), "c")) {
            players[1] = new ComputerPlayer(1);
        } else {
            players[1] = new HumanPlayer(1);
        }



        while (chessBoard.isEnd() == 0 && !chessBoard.isFull()) {
            chessBoard.printBoard();
            int operation = players[nowPlayer].input(chessBoard);
            if (operation >= 0) { // 正常落子
                chessBoard.storeData(operation, players[nowPlayer].getIdentity()); // 存储棋子
                chessBoard.addHistory(operation); // 添加历史记录
            } else if (operation == -1) { // 悔棋
                chessBoard.regret(); // 调用悔棋接口
                if (Main.findPlayer(-1 * players[nowPlayer].getIdentity()).getName().equals("Computer")) {
                    chessBoard.regret(); // 如果对手是电脑则悔棋两步
                    Main.changeNowPlayer(); // 悔棋两步后仍然为玩家操作
                }
            }
            changeNowPlayer(); // 改变操作者
            System.out.println("----------------------"); // 分割线
        }

        chessBoard.printBoard();
        if (chessBoard.isEnd() == 0 && chessBoard.isFull()) {
            System.out.println("Tow players draw!");
        } else {
            System.out.println("Congratulations!");
            System.out.printf("Winner is player%d", (chessBoard.isEnd() + 3) / 2);
        }
    }
}