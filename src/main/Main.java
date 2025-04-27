package main;

import data.Map;
import data.block.SandBlock;
import java.util.Scanner;

public class Main {

    private static final int INTERACTIONS = 10;

    public static void main(String[] args) {
        MainView m = new MainView();
        m.display_on_out();
        for (int i = 0; i < INTERACTIONS; i++) {
            System.out.print("Enter row and then column, or enter '9' and then '9' for smelting: ");
            Scanner myObj = new Scanner(System.in);
            int row = myObj.nextInt();
            int col = myObj.nextInt();
            if (row == 9 && col == 9) {
                m.smelt();
            } else {
                m.move_into_furnace(row, col);
            }
            m.display_on_out();
        }
    }
}
