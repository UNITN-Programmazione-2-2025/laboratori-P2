package data.block;

public class NullBlock extends AbstractSolidBlock implements Smeltable {
    public NullBlock() {
        super("Null Block", '/');
    }

    @Override
    public Block smelt() {
        return this;
    }
}
