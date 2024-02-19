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
            this.solution = Algorithms.DFID(this.start, goal, this.printOpenList);
            time = System.nanoTime()-time;
        }
        else if (this.algorithm.equals("A*")) {
            time = System.nanoTime();
            this.solution = Algorithms.A_Star(this.start, goal, this.printOpenList);
            time = System.nanoTime()-time;
        }
        else if (this.algorithm.equals("IDA*")) {
            time = System.nanoTime();
            this.solution = Algorithms.IDA_Star(this.start, goal, this.printOpenList);
            time = System.nanoTime()-time;
        }
        else if (this.algorithm.equals("DFBnB")) {
            time = System.nanoTime();
            this.solution = Algorithms.DFBnB(this.start, goal, this.printOpenList);
            time = System.nanoTime()-time;
        }

    }

    public String getSolution()
    {
        String[] sol = this.solution.split(",");
        String ret = sol[0];
        if (sol.length > 1) {
            this.numOfNodes = Integer.parseInt(sol[1]);
            ret += "\nNum: "+this.numOfNodes;
            if (sol[0].equals("no path")) {
                ret += "\nCost: ";
            }
            else {
                this.cost = Integer.parseInt(sol[2]);
                ret += "\nCost: "+this.cost;
            }
        }
        if (this.withTime) ret += "\n"+String.format("%.3f",(double)time/1000000000)+" seconds";
        return ret;
    }

}