public class Solver {

    private Node start;
    private String algorithm;
    private boolean withTime;
    private boolean printOpenList;
    private int cost = 0;
    private int numOfNodes = 0;
    private Board board;
    private String solution = "";
    long time;


    public Solver(Board b, String alg, String wt, String pol)
    {
        this.board = b;
        this.algorithm = alg;
        this.withTime = wt.contains("no") ? false : true;
        this.printOpenList = pol.contains("no") ? false : true;
        this.start = new Node(this.board);
    }

    public void solve()
    {
        Node goal = new Node(new Board(Algorithms.goalString(this.board.getSize()), ""));
        time = System.nanoTime();
        
        if (this.algorithm.equals("DFID")) {
            this.solution = Algorithms.DFID(this.start, goal);
        }

        time = System.nanoTime()-time;
    }

    public String getSolution()
    {
        return solution;
    }

}