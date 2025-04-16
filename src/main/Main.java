import data.Block;
import data.Map;

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

            System.out.println("Changing: "+row+" - "+col);
            map.insert_cell(row,col, new Block((char) ('A' + i), i%2 == 0, false));

            map.display_on_out();
        }

    }
}