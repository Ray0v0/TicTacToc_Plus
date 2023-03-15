import java.util.Scanner;

public class HumanPlayer extends Player{

    public HumanPlayer(int identity) {
        super(identity);
    }
    @Override
    public int input(Board chessBoard) {
        Scanner in = new Scanner(System.in);
        boolean isInputLegal = true;
        int size = chessBoard.getSize(), ret;
        do {
            System.out.printf("Player%d\n", (getIdentity() + 3) / 2);
            if (!isInputLegal) System.out.println("Input is illegal, please try again!");
            System.out.print("Row: ");
            int row = in.nextInt();
            System.out.print("Col: ");
            int col = in.nextInt();
            ret = (row - 1) * size + col - 1;
            isInputLegal = (0 <= ret && ret < size * size) && (chessBoard.getStorage(ret) == 0);
        } while (!isInputLegal);
        return ret;
    }

}
