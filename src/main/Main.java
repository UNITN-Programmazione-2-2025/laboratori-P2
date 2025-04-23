package main;

import data.Map;
import data.block.SandBlock;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map map = new Map(10, 10);

        for (int i = 0; i < 10; i++) {
            System.out.print("Enter row: ");
            Scanner myObj = new Scanner(System.in);
            int row = myObj.nextInt();

            System.out.print("Enter column: ");
            int col = myObj.nextInt();

            SandBlock sandBlock = new SandBlock();
            System.out.println("Putting " + sandBlock + " at (" + row + ", " + col + ")");
            map.insert_rec(row, col, sandBlock);

            map.display_on_out();
        }
    }
}