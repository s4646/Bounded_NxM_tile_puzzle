public class Tile {
    private String content;
    private int type; // red = 0, white = 1, empty = 2
    private int movesLeft; // if white, how many moves left
    
    public Tile()
    {
        this.content = "_";
        this.type = 2;
    }

    public Tile(String c)
    {
        this.content = c;
        this.type = c.equals("_") ? 2 : 0; // if '_' set to empty
    }

    public Tile(String c, int ml)
    {
        this.content = c;
        this.type = 1;
        this.movesLeft = ml;
    }

    public Tile(String c, int t, int ml) // clone
    {
        this.content = c;
        this.type = 1;
        this.movesLeft = ml;
    }

    public void setContent(String s)
    {
        this.content = s;
    }

    public void setType(int x)
    {
        this.type = x;
    }

    public void setMoves(int x)
    {
        this.movesLeft = x;
    }

    public String getContent()
    {
        return this.content;
    }

    public int getType()
    {
        return this.type;
    }

    public int getMovesLeft()
    {
        return this.movesLeft;
    }

    public Tile clone()
    {
        return new Tile(this.content, this.type, this.movesLeft);
    }
}
