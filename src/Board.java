public class Board {
    private final int size;
    private int[] storage = new int[100];
    public Board(int setSize) {
        this.size = setSize;
    }

    public int getSize() {
        return size;
    }

    public int getStorage(int index) {
        return storage[index];
    }

    public void storeData(int index, int data) {
        storage[index] = data;
    }

    public void printBoard() {
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.printf(" %d ", i + 1); //列提示
        }
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
    }

    private boolean isWin(int player) {
        boolean ret = false;
        boolean flag = false;
        if (!ret) {

            for (int i = 0; i < size; i++) {
                flag = true;
                for (int j = 0; j < size * size; j += size) {
                    if (storage[i + j] != player) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    ret = true;
                }
            }

        }
        if (!ret) {
            for (int i = 0; i < size * size; i += size) {
                flag = true;
                for (int j = 0; j < size; j++) {
                    if (storage[i+j] != player) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    ret = true;
                }
            }

        }
        if (!ret) {
            flag = true;
            for (int i = 0; i < size * size; i += size + 1) {
                if (storage[i] != player) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ret = true;
            }
        }
        if (!ret) {
            flag = true;
            for (int i = size - 1; i < size * size - 1; i += (size - 1)) {
                if (storage[i] != player) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ret = true;
            }
        }
        return ret;
    }

    public int isEnd() {
        int ret = 0;
        if (isWin(1)) {
            ret = 1;
        } else if (isWin(-1)) {
            ret = -1;
        }
        return ret;
    }

    public boolean isFull() {
        boolean ret = true;
        for (int i = 0; i < size * size; i++) {
            if (storage[i] == 0) {
                ret = false;
                break;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Board testBoard = new Board(5);
        testBoard.storage[4] = 1;
        testBoard.storage[8] = 1;
        testBoard.storage[12] = 1;
        testBoard.storage[16] = 1;
        testBoard.storage[20] = 1;
        System.out.println(testBoard.isWin(1));
    }
}
