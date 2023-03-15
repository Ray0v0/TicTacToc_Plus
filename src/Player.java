import java.util.Scanner;

public class Player {
    private final int identity;
    public Player(int identity) {
        this.identity = identity;
    }
    public int input(Board chessBoard) {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public int getIdentity() {
        return identity;
    }
}
