public class Node {
    private Board board;
    private Node[] next;

    public Node(Board b) // constructor to start node
    {
        this.board = b;
        this.next = new Node[4];
    }

    public Node(String b, String w)
    {
        this.board = new Board(b, w);
        this.next = new Node[4];
    }
}
