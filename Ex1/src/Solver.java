public class Solver {

    private Node start;
    private String algorithm;
    private boolean withTime;
    private boolean printOpenList;
    private int cost = 0;
    private int numOfNodes = 0;
    private Board board;


    public Solver(Board b, String alg, String wt, String pol)
    {
        this.board = b;
        this.algorithm = alg;
        this.withTime = wt.contains("no") ? false : true;
        this.printOpenList = pol.contains("no") ? false : true;
        this.start = new Node(this.board);
    }

    public void solve(String alg)
    {
        Node goal = new Node(new Board(Algorithms.goalString(this.board.getSize()), ""));
        String solution;
        if (alg.equals("DFID")) {
            solution = Algorithms.dfid(this.start, goal);
        }
    }

}