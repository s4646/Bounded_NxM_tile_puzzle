public class Node {
    private Board board;
    private Node[] next;

    public Node(Board b)
    {
        this.board = b;
        this.next = new Node[4];
    }
}
