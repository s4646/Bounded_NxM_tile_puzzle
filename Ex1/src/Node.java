import java.util.LinkedList;

public class Node implements Comparable<Node>{
    private Board board;
    private Node prev;
    private LinkedList<Node> next;
    private boolean whiteTileMoved;
    private int cost;

    public Node(Board b) // constructor to start node
    {
        this.board = b;
        this.prev = null;
        this.next = new LinkedList<Node>();
        whiteTileMoved = false;
        cost = 0;
    }

    public Node(String b, String w)
    {
        this.board = new Board(b, w);
        this.prev = null;
        this.next = new LinkedList<Node>();
        whiteTileMoved = false;
        cost = 0;
    }

    public Board getBoard()
    {
        return this.board;
    }

    public void addNext(Node n)
    {
        this.next.add(n);
    }

    public void setPrev(Node n)
    {
        this.prev = n;
    }

    public Node getPrev()
    {
        return this.prev;
    }

    public void setWhiteTileMoved(boolean b)
    {
        this.whiteTileMoved = b;
    }

    public boolean isWhiteTileMoved()
    {
        return this.whiteTileMoved;
    }

    public void setCost(int c)
    {
        this.cost = c;
    }

    public int getCost()
    {
        return this.cost;
    }

    public int ManhattenDistance(Node n) // Heuristic Function
    {
        int dist = 0;
        int [] boardSize = this.getBoard().getSize();
        Tile[][] tiles = n.getBoard().getTiles();
        
        for (int i = 0; i < boardSize[0]; i++) {                 // rows -> Y
            for (int j = 0; j < boardSize[1]; j++) {             // cols -> X
                 if (tiles[i][j].getType() == 2) // Empty Tile
                    continue;
                int value = Integer.parseInt(tiles[i][j].getContent());
                dist += Math.abs(i - (value-1) / boardSize[0]) + Math.abs(j - (value-1) % boardSize[1]);
            
            }
        }
        return dist;
    }

    @Override
    public String toString()
    {
        return this.board.toString();
    }

    @Override
    public int compareTo(Node ot)
    {
        return Integer.compare(this.cost + ManhattenDistance(this), ot.getCost() + ManhattenDistance(ot));
    }
}
