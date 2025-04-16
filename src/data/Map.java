package data;

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
                map[row][column] = new Block();
            }
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
        for (int i = row;  i < columns-1; i++) {
            if (map[i][column].isFalls_with_gravity() & map[i+1][column].isFalls_through()) {
               swap_cell(i, column);
            }
        }
    }

    public void insert_rec(int row, int column, Block cell) {
        if (!cell.isFalls_with_gravity() || row == rows - 1 || !map[row+1][column].isFalls_through()) {
            map[row][column] = cell;
            return;
        }

        insert_rec(row+1, column, cell);
    }
}
