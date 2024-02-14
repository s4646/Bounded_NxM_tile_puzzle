import java.util.LinkedList;

public class Node {
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
}
