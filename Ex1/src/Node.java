public class Node {
    private Board board;
    private Node[] next;

    public Node(Board b) // constructor to start node
    {
        this.board = b;
    }

    public Node(String b, String w)
    {
        this.board = new Board(b, w);
    }

    public Board getBoard()
    {
        return this.board;
    }
    
    public void createNextArray(int size)
    {
        this.next = new Node[size];
    }
}
