package data;

public class Block {
    private final char content;
    private final boolean falls_with_gravity;
    private final boolean falls_through;

    public Block(){
        this.content = '.';
        this.falls_with_gravity = false;
        this.falls_through = true;
    }

    public Block(char content, boolean falls_with_gravity, boolean falls_through) {
        this.content = content;
        this.falls_with_gravity = falls_with_gravity;
        this.falls_through = falls_through;
    }

    public char display() {
        return content;
    }

    public boolean isFalls_with_gravity() {
        return falls_with_gravity;
    }

    public boolean isFalls_through() {
        return falls_through;
    }
}
