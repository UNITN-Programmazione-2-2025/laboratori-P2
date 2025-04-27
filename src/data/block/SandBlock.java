package data.block;

public class SandBlock extends AbstractBlock implements Smeltable {

    public SandBlock() {
        super("Sand Block", 'S', true, false);
    }

    @Override
    public Block smelt() {
        return new GlassBlock();
    }
}
