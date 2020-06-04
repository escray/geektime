public class CoinChange {
    public int minCoins(int money) {
        if (money == 1 || money == 3 || money == 5) {
            return 1;
        }
        boolean[][] state = new boolean[money][money + 1];
        if (money >= 1) {
            state[0][1] = true;
        }
        if (money >= 3) {
            state[0][3] = true;
        }
        if (money >= 5) {
            state[0][5] = true;
        }
        for (int i = 1; i < money; i++) {
            for (int j = 1; j < money; j++) {
                if (state[i - 1][j]) {
                    if (j + 1 <= money) {
                        state[i][j+1] = true;
                    }
                    if (j + 3 <= money) {
                        state[i][j+3] = true;
                    }
                    if (j + 5 <= money) {
                        state[i][j+5] = true;
                    }
                    if (state[i][money]) {
                        return i + 1;
                    }
                }
            }
        }
        return money;
    }
}
