package main;

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

            System.out.println("Changing: " + row + " - " + col);

            char content = (char) ('A' + i); // imposta il contenuto con lettere dell'alfabeto consecutive
            boolean falls_with_gravity = i % 2 == 0; // alterna blocchi con gravità e senza
            map.insert_rec(row, col, new Block(content, falls_with_gravity, false));

            map.display_on_out();
        }
    }
}