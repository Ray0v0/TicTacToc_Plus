import java.util.Scanner;

public class HumanPlayer extends Player{

    public HumanPlayer(int identity) {
        super(identity, "Human");
    }
    @Override
    public int input(Board chessBoard) {
        Scanner in = new Scanner(System.in);
        boolean isInputLegal = true;
        int size = chessBoard.getSize(), ret;
        do {
            System.out.printf("Player%d\n", (getIdentity() + 3) / 2);
            if (!isInputLegal) System.out.println("Input is illegal, please try again!");
            System.out.print("Row Col:");
            if (! in.hasNextInt()) {
                isInputLegal = false;
                ret = -1; // 非正常下棋标记
                String operation = in.next();
                if (operation.equals( "regret")) {
                    isInputLegal = true;
                }
            } else {
                int row = in.nextInt();
                int col = in.nextInt();
                ret = (row - 1) * size + col - 1; // 下棋位置
                isInputLegal = (0 <= ret && ret < size * size) && (chessBoard.getStorage(ret) == 0);
            }
        } while (!isInputLegal);
        return ret;
    }

}
