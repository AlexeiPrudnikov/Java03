public class Main {
    static int[][] steps = {{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};

    public static void printGround(int[][] ground) {
        for (int i = 0; i < ground.length; i++) {
            for (int j = 0; j < ground[i].length; j++) {
                System.out.print(ground[i][j] + "\t");
            }
            System.out.println();
        }
    }
    public static void printDesc(String[][] desc) {
        for (int i = 0; i < desc.length; i++) {
            for (int j = 0; j < desc[i].length; j++) {
                System.out.print(desc[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int size = 5;
        int[][] ground = new int[size][size];
        horseSpeps(ground, 1, 2, 2);
        System.out.println("Обход конем поля 5 на 5");
        printGround(ground);
        System.out.println();
        size = 8;
        String[][] desc = new String[size][size];
        for (int i = 0; i < desc.length; i++) {
            for (int j = 0; j < desc[i].length; j++) {
                desc[i][j] = "-";
            }
        }
        standQueens(desc, 0);
        System.out.println("Расстановка 8 ферзей на шахматной доске");
        printDesc(desc);
    }

    // Задача расстановки ферзей
    public static boolean isValidQueenStep(String[][] desc, int x, int y) {

        for (int i = 0; i < desc.length; i++) {
            if (desc[i][y] == "*") return false;
        }
        // Диагональ слева направо
        int x0 = 0;
        int y0 = 0;
        if ((x - y) >= 0) {
            x0 = x - y;
            y0 = 0;
        } else {
            x0 = 0;
            y0 = y - x;
        }
        do {
            if (desc[x0][y0] == "*") return false;
            x0++;
            y0++;
        } while ((x0 < desc.length) && (y0 < desc[0].length));

        // Диагональ справа налево
        if ((x + y) >= desc.length) {
            x0 = desc.length - 1;
            y0 = (x + y) - (desc[0].length - 1);
        } else {
            x0 = x + y;
            y0 = 0;
        }
        do {
            if (desc[x0][y0] == "*") return false;
            x0--;
            y0++;
        } while ((x0 >= 0) && (y0 < desc.length));

        return true;
    }

    public static int standQueens(String[][] desc, int step) {
        if (step >= desc.length) return step;
        int currentStep = step;
        for (int i = 0; i < desc.length; i++) {
            if ((i == 7) && (step == 4)) {
                int zx = 123;
            }
            if (isValidQueenStep(desc, step, i)) {
                desc[step][i] = "*";
                step++;
                step = standQueens(desc, step);
                if (currentStep >= step) desc[step][i] = "-";
            }
        }
        return step - 1;
    }

    // Задача обхода конем доски
    public static int horseSpeps(int[][] groung, int step, int x, int y) {
        groung[y][x] = step;
        if (step >= groung.length * groung[0].length) return step;
        int currentStep = step;
        int resStep = 0;
        boolean flag;
        for (int[] item : steps) {
            flag = false;
            if (step == 1)
                flag = true;
            if (((x + item[0]) >= 0) && ((y + item[1]) >= 0) &&
                    ((x + item[0]) < groung[0].length) && ((y + item[1]) < groung.length) &&
                    (groung[y + item[1]][x + item[0]] == 0)
            ) {
                step++;
                step = horseSpeps(groung, step, x + item[0], y + item[1]);
            }
        }
        if (currentStep >= step) groung[y][x] = 0;
        return step - 1;
    }
}