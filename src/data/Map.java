package data;

import java.util.Random;

import data.block.AirBlock;
import data.block.Block;
import data.block.SandBlock;
import data.block.Smeltable;
import data.block.WaterBlock;

public class Map {

    private final Block[][] map;
    private final int rows;
    private final int columns;

    public Map(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        map = new Block[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                map[row][column] = new AirBlock();
            }
        }

        add_river();

        randomize(rows * columns / 10);
    }

    public void randomize(int count) {
        Random rand = new Random();
        for (int i = 0 ; i < count; i++){
            Block b = new SandBlock();
            int row = rand.nextInt(rows);
            int col = rand.nextInt(columns);
            insert_rec(row, col, b);
        }
    }

    public void display_on_out() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                System.out.print(map[row][column].display() + " ");
            }
            System.out.println();
        }
    }

    public void change_cell(int row, int column, Block cell, boolean apply_gravity) {
        map[row][column] = cell;
    }

    private void swap_cell(int row, int column) {
        Block tmp = map[row + 1][column];
        map[row + 1][column] = map[row][column];
        map[row][column] = tmp;
    }

    public void insert_cell(int row, int column, Block cell) {
        map[row][column] = cell;
        for (int i = row; i < columns - 1; i++) {
            if (map[i][column].isFalls_with_gravity() && map[i + 1][column].isFalls_through()) {
                swap_cell(i, column);
            }
        }
    }

    public void insert_rec(int row, int column, Block cell) {
        if (!cell.isFalls_with_gravity() || row == rows - 1 || !map[row + 1][column].isFalls_through() || map[row + 1][column].display() == cell.display()) {
            map[row][column] = cell;
            return;
        }

        insert_rec(row + 1, column, cell);
    }

    private void add_rows_of_water() {
        for (int column = 0; column < columns; column++) {
            insert_rec(0, column, new WaterBlock());
        }
    }

    public void add_river() {
        add_rows_of_water();
    }

    public void add_sea() {
        for (int i = 0; i < 3; i++) {
            add_rows_of_water();
        }
    }

    public Block get_block(int r, int c) {
        return map[r][c];
    }

    public boolean is_smeltable(int r, int c) {
        return map[r][c] instanceof Smeltable;
    }

    public Smeltable get_smeltable(int r, int c) {
        return (Smeltable) map[r][c];
    }
}
