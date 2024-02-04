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
        this.type = 0;
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
}
