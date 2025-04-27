package main;

import data.Furnace;
import data.Map;
import data.block.Smeltable;

public class MainView {
    private final Map map;
    private final Furnace furnace;

    public MainView() {
        this(10, 10);
    }

    public MainView(int rows, int columns) {
        this(new Map(rows, columns));
    }

    public MainView(Map map) {
        this.map = map;
        this.furnace = new Furnace();
    }

    public void display_on_out() {
        map.display_on_out();
        furnace.display_on_out();
    }

    public void move_into_furnace(int r, int c) {
        if (map.is_smeltable(r, c)) {
            Smeltable block = map.get_smeltable(r, c);
            furnace.set_input(block);
        }
    }

    public void smelt() {
        furnace.smelt();
    }
}
