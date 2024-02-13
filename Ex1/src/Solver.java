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
        
        if (this.algorithm.equals("DFID")) {
            time = System.nanoTime();
            this.solution = Algorithms.DFID(this.start, goal);
            time = System.nanoTime()-time;
        }

    }

    public String getSolution()
    {
        String[] sol = this.solution.split(",");
        if (sol.length >= 2) {
            this.solution = sol[0];
            this.numOfNodes = Integer.parseInt(sol[1]);
            return this.solution+"\nnum: "+this.numOfNodes+"\ntime: "+String.format("%.3f",(double)time/1000000000);
        }
        else return sol[0];
    }

}