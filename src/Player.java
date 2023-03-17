import java.util.Scanner;

public class Player {
    private final int identity; // identity 为 1 或 -1, 体现对抗
    private final String name; // name 为 Human 或 Computer
    public Player(int identity, String name) {
        this.identity = identity;
        this.name = name;
    } // 初始化定义 identity 和 name
    public int input(Board chessBoard) {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    } // 下棋输入接口
    public String getName() {
        return name;
    } // 对外展示 name

    public int getIdentity() {
        return identity;
    } // 对外展示 identity
}
