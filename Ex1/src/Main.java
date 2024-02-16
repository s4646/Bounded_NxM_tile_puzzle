// import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");
        InputReader myReader = new InputReader();
        myReader.getInput("input.txt");
        // System.out.println(myReader.toString());
        
        Board myBoard = new Board(myReader.getBoard(), myReader.getWhiteTiles());
        Solver mySolver = new Solver(myBoard, myReader.getAlgorithm(), myReader.getWithTime(), myReader.getPrintOpenList());
        mySolver.solve();
        System.out.println(mySolver.getSolution());
    }
}
