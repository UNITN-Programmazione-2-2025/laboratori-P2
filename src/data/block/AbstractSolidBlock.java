package data.block;

public class AbstractSolidBlock extends AbstractBlock {
    protected AbstractSolidBlock(String blockname, char content) {
        super(blockname, content, false, false);
    }
}
