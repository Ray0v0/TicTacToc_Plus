import java.util.Random;

public class ComputerPlayer extends Player{
    public ComputerPlayer(int identity) {
        super(identity, "Computer");
    }
    private int countBlank(Board chessBoard) {
        int count = 0;
        for (int i = 0; i < chessBoard.getSize() * chessBoard.getSize(); i++) {
            if (chessBoard.getStorage(i) == 0) count++;
        }
        return count;
    }

    @Override
    public int input(Board chessBoard) {
        int condition = countBlank(chessBoard);
        if (condition > 9) {
            return findBestChoiceQuickly(chessBoard);
        } else {
            return findBestChoice(chessBoard);
        }
    }
    public int findBestChoiceQuickly(Board chessBoard) {
        Random ran = new Random();
        int maxValue = -2 * getIdentity();
        int size = chessBoard.getSize();
        int ret = -1;
        for (int i = 0; i < size * size; i++) {
            if (chessBoard.getStorage(i) == 0) {
//                System.out.printf("Searching index %d\n", i);
                chessBoard.storeData(i, getIdentity());
                if (chessBoard.isEnd() == getIdentity()) {
                    ret = i;
                    chessBoard.storeData(i, 0);
                    break;
                }
                chessBoard.storeData(i, 0);
            }
        }
        if (ret == -1) {
            for (int i = 0; i < size * size; i++) {
                if (chessBoard.getStorage(i) == 0) {
                    chessBoard.storeData(i, -1 * getIdentity());
                    if (chessBoard.isEnd() == -1 * getIdentity()) {
                        ret = i;
                        chessBoard.storeData(i, 0);
                        break;
                    }
                    chessBoard.storeData(i, 0);
                }
            }
        }
        if (ret == -1) {
            int begin = ran.nextInt(size * size);
            for (int i = 0; i < size * size; i++) {
                int index = (begin + i) % (size * size);
                if (chessBoard.getStorage(index) == 0) {
                    chessBoard.storeData(index, getIdentity());
                    int value = alpha_beta_quickly(chessBoard, -1 * getIdentity(), -2, 2);
                    chessBoard.storeData(index, 0);
                    if (getIdentity() * value >= getIdentity() * maxValue) {
                        maxValue = value;
                        ret = index;
                        if (maxValue == 0) {
                            break;
                        }
                    }
                }
            }
        }
        if (ret == -1) {
            System.out.println(getIdentity());
            System.out.println(maxValue);
        }
//        System.out.println(maxValue);
        return ret;
    }
    public int findBestChoice(Board chessBoard) {
        Random ran = new Random();
        int maxValue = -2 * getIdentity();
        int size = chessBoard.getSize();
        int[] ret = new int[25];
        int countRet = 0;
        for (int i = 0; i < size * size; i++) {
            if(chessBoard.getStorage(i) == 0) {
//                System.out.printf("Searching index %d\n", i);
                chessBoard.storeData(i, getIdentity());
                if (chessBoard.isEnd() != 0) {
                    ret[0] = i;
                    countRet = 1;
                    break;
                } else {
                    int value = alpha_beta(chessBoard, -1 * getIdentity(), -2, 2);
                    chessBoard.storeData(i, 0);
                    if (getIdentity() * value > getIdentity() * maxValue) {
                        maxValue = value;
                        ret[0] = i;
                        countRet = 1;
                    } else if (value == maxValue){
                        ret[countRet] = i;
                        countRet += 1;
                    }
                }
            }
        }
//        System.out.println(maxValue);
        return ret[ran.nextInt(countRet)];
    }

    private int alpha_beta(Board chessBoard, int nowPlayer, int alpha, int beta) {
        int ret;
//        OUT:
        if (chessBoard.isEnd() != 0) {
            ret = chessBoard.isEnd();
        } else if (chessBoard.isFull()) {
            ret = 0;
        } else {
            int size = chessBoard.getSize();
            for (int i = 0; i < size * size; i++) {
                if (chessBoard.getStorage(i) == 0) {
                    chessBoard.storeData(i, nowPlayer);
                    int value = alpha_beta(chessBoard, -1 * nowPlayer, alpha, beta);
                    chessBoard.storeData(i, 0);
                    if (nowPlayer == 1) {
                        if (value > alpha) {
                            alpha = value;
                        }
                        if (alpha >= beta) {
                            ret = alpha;
//                            break OUT;
                        }
                    } else {
                        if (value < beta) {
                            beta = value;
                        }
                        if (beta <= alpha) {
                            ret = beta;
//                            break OUT;
                        }
                    }
                }
            }
            if (nowPlayer == 1) {
                ret = alpha;
            } else {
                ret = beta;
            }
        }
        return ret;
    }


    private int alpha_beta_quickly(Board chessBoard, int nowPlayer, int alpha, int beta) {
        int ret;
        OUT:
        if (chessBoard.isEnd() != 0) {
            ret = chessBoard.isEnd();
        } else if (chessBoard.isFull()) {
            ret = 0;
        } else {
            int size = chessBoard.getSize();
            for (int i = 0; i < size * size; i++) {
                if (chessBoard.getStorage(i) == 0) {
                    chessBoard.storeData(i, nowPlayer);
                    int value = alpha_beta_quickly(chessBoard, -1 * nowPlayer, alpha, beta);
                    chessBoard.storeData(i, 0);
                    if (value == 0) {
                        return 0;
                    }
                    if (nowPlayer == 1) {
                        if (value > alpha) {
                            alpha = value;
                        }
                        if (alpha >= beta) {
                            ret = alpha;
                            break OUT;
                        }
                    } else {
                        if (value < beta) {
                            beta = value;
                        }
                        if (beta <= alpha) {
                            ret = beta;
                            break OUT;
                        }
                    }
                }
            }
            if (nowPlayer == 1) {
                ret = alpha;
            } else {
                ret = beta;
            }
        }
        return ret;
    }
}
