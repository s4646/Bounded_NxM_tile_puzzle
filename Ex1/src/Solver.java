public class Solver {

    private String algorithm;
    private boolean withTime;
    private boolean printOpenList;
    private Board board;

    public Solver(Board b, String alg, String wt, String pol)
    {
        this.board = b;
        this.algorithm = alg;
        this.withTime = wt.contains("no") ? false : true;
        this.printOpenList = pol.contains("no") ? false : true;
    }

    public void solve(Board b, String alg)
    {
        // TODO
    }

}