import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please input the size of chessboard [3, 9]: ");
        Board chessBoard = new Board(in.nextInt());

        Player[] players = new Player[2];
        System.out.print("Please choose player1, human(h) or computer(c)? Default is human :");
        if (Objects.equals(in.next(), "c")) {
            players[0] = new ComputerPlayer(-1);
        } else {
            players[0] = new HumanPlayer(-1);
        }

        System.out.print("Please choose player2, human(h) or computer(c)? Default is human :");
        if (Objects.equals(in.next(), "c")) {
            players[1] = new ComputerPlayer(1);
        } else {
            players[1] = new HumanPlayer(1);
        }

        int nowPlayer = 0;

        while (chessBoard.isEnd() == 0 && !chessBoard.isFull()) {
            chessBoard.printBoard();
            chessBoard.storeData(players[nowPlayer].input(chessBoard), players[nowPlayer].getIdentity());
            nowPlayer = 1 - nowPlayer;
            System.out.println("----------------------");
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