public class Board {
    private final int size; // 棋盘大小
    private int[] storage = new int[100]; // 棋盘

    private int[] history = new int[100]; // 历史记录
    private int countHistory = 0; // 历史记录条目数
    public Board(int setSize) {
        this.size = setSize;
    } // 初始化棋盘及其大小

    public void regret() {
        storage[history[countHistory]] = 0;
        delHistory();
    } // 悔棋接口, 实现删除一条历史记录

    public void addHistory(int index) {
        countHistory++;
        history[countHistory] = index;
    } // 添加历史记录

    public void delHistory() {
        if (countHistory > 0) {
            history[countHistory] = 0;
            countHistory--;
        } else {
            System.out.println("Error: No such history.");
            Main.changeNowPlayer();
        }
    } // 删除历史记录, 判断历史记录是否为空, 报错

    public int getSize() {
        return size;
    } // 对外展示 size

    public int getStorage(int index) {
        return storage[index];
    } // 获得棋盘位置 index 上的棋子

    public void storeData(int index, int data) {
        storage[index] = data;
    } // 在棋盘位置 index 上下棋

    public void printBoard() {
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.printf(" %d ", i + 1);
        } //列提示
        for (int i = 0; i < size * size; i++) {
            if (i % size == 0) {
                System.out.println(); //换行
                System.out.printf(" %d ", i / size + 1); //行提示
            }
            char c = '.';
            if (storage[i] == 1) c = 'X';
            if (storage[i] == -1) c = 'O';
            System.out.printf(" %c ", c);
        }
        System.out.println();
    }  // 打印棋盘

    private boolean isWin(int identity) {
        boolean ret = false;
        boolean flag = false;
        if (!ret) {
            for (int i = 0; i < size; i++) {
                flag = true;
                for (int j = 0; j < size * size; j += size) {
                    if (storage[i + j] != identity) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    ret = true;
                }
            }

        } // 纵向
        if (!ret) {
            for (int i = 0; i < size * size; i += size) {
                flag = true;
                for (int j = 0; j < size; j++) {
                    if (storage[i+j] != identity) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    ret = true;
                }
            }

        } // 横向
        if (!ret) {
            flag = true;
            for (int i = 0; i < size * size; i += size + 1) {
                if (storage[i] != identity) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ret = true;
            }
        } // 主对角线
        if (!ret) {
            flag = true;
            for (int i = size - 1; i < size * size - 1; i += (size - 1)) {
                if (storage[i] != identity) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ret = true;
            }
        } // 副对角线
        return ret;
    } // 判断某个 identity 的 player 是否获胜

    public int isEnd() {
        int ret = 0;
        if (isWin(1)) {
            ret = 1;
        } else if (isWin(-1)) {
            ret = -1;
        }
        return ret;
    } // 判断棋局状态, 若某位玩家获胜则返回其 identity, 否则返回 0

    public boolean isFull() {
        boolean ret = true;
        for (int i = 0; i < size * size; i++) {
            if (storage[i] == 0) {
                ret = false;
                break;
            }
        }
        return ret;
    } // 判断棋盘是否已满

    public static void main(String[] args) {
        Board testBoard = new Board(5);
        testBoard.storage[4] = 1;
        testBoard.storage[8] = 1;
        testBoard.storage[12] = 1;
        testBoard.storage[16] = 1;
        testBoard.storage[20] = 1;
        System.out.println(testBoard.isWin(1));
    } // 测试样例
}
