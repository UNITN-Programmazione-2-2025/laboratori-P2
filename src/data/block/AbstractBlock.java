package data.block;

public abstract class AbstractBlock implements Block {

    protected final String blockname;
    protected final char content;
    protected final boolean falls_with_gravity;
    protected final boolean falls_through;

    protected AbstractBlock(String blockname, char content, boolean falls_with_gravity, boolean falls_through) {
        this.blockname = blockname;
        this.content = content;
        this.falls_with_gravity = falls_with_gravity;
        this.falls_through = falls_through;
    }

    @Override
    public char display() {
        return content;
    }

    @Override
    public boolean isFalls_with_gravity() {
        return falls_with_gravity;
    }

    @Override
    public boolean isFalls_through() {
        return falls_through;
    }

    @Override
    public String toString() {
        return String.format(
            "[%s] %s, falls_with_gravity: %s, falls_through: %s",
            content,
            blockname,
            falls_with_gravity ? "yes" : "no",
            falls_through ? "yes" : "no"
        );
    }
}
