package data;
import data.block.Block;
import data.block.NullBlock;
import data.block.Smeltable;

public class Furnace {
    private Smeltable input;
    private Block output;

    public Furnace() {
        this.input = new NullBlock();
        this.output = new NullBlock();
    }

    public void display_on_out() {
        System.out.println("|| " + this.input.display() + " --> " + this.output.display() + " ||");
    }

    public void smelt() {
        output = input.smelt();
        input = new NullBlock();
    }

    public void set_input(Smeltable input) {
        this.input = input;
    }
}
