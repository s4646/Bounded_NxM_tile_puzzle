// import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");
        InputReader myReader = new InputReader();
        myReader.getInput("input.txt");
        System.out.println(myReader.toString());
        
        Board myBoard = new Board(myReader.getBoard(), myReader.getWhiteTiles());
        Solver mySolver = new Solver(myBoard, myReader.getAlgorithm(), myReader.getWithTime(), myReader.getPrintOpenList());
        mySolver.solve();
        System.out.println(mySolver.getSolution());


        
        // System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // Board myBoard = new Board(myReader.getBoard(), myReader.getWhiteTiles());
        // System.out.println(myBoard.toString());

        // Node n = new Node(myBoard);
        // LinkedList<Character> l = Algorithms.getValidOperators(n);
        // System.out.println(n.getBoard().getTiles());
        // for (Character character : l) {
        //     System.out.println(character);
        //     Node test = Algorithms.operate(n, character);
        //     // System.out.println(test.getBoard().getBoard());
        //     System.out.println(test.getBoard().toString());
        //     // System.out.println(test.getBoard().whiteTilesToString());
        // }
    }
}
