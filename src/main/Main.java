package main;

import java.util.Scanner;

public class Main {

    private static final int INTERACTIONS = 10;

    public static void main(String[] args) {
        MainView view = new MainView();
        view.display_on_out();
        
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < INTERACTIONS; i++) {
            System.out.print("Enter row and then column, or enter '9' and then '9' for smelting: ");
            int row = input.nextInt();
            int col = input.nextInt();
            if (row == 9 && col == 9) {
                view.smelt();
            } else {
                view.move_into_furnace(row, col);
            }
            view.display_on_out();
        }
    }
}
