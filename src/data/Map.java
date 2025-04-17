package data;

public class Map {
    private final Block[][] map;
    private final int width;
    private final int height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        map = new Block[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y] = new Block();
            }
        }
    }

    public void display_on_out() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                System.out.print(map[x][y].display() + " ");
            }
            System.out.println();
        }
    }

    public void change_cell(int x, int y, Block cell, boolean apply_gravity) {
        map[x][y] = cell;
    }

    private void swap(int x, int y) {
        Block tmp = map[x + 1][y];
        map[x + 1][y] = map[x][y];
        map[x][y] = tmp;
    }

    public void insert_cell(int x, int y, Block cell) {
        map[x][y] = cell;
        for (int i = x;  i < height-1; i++) {
            if (map[i][y].isFalls_with_gravity() && map[i+1][y].isFalls_through()) {
               swap(i, y);
            }
        }
    }

    public void insert_rec(int x, int y, Block cell) {
        if (!cell.isFalls_with_gravity() || x == height - 1 || !map[x+1][y].isFalls_through()) {
            map[x][y] = cell;
            return;
        }

        insert_rec(x+1, y, cell);
    }
}
