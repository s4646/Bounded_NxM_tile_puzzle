import java.io.FileWriter;

public class Ex1 {
    public static void main(String[] args) throws Exception {
        InputReader myReader = new InputReader();
        myReader.getInput("input.txt");
        
        Board myBoard = new Board(myReader.getBoard(), myReader.getWhiteTiles());
        Solver mySolver = new Solver(myBoard, myReader.getAlgorithm(), myReader.getWithTime(), myReader.getPrintOpenList());
        mySolver.solve();

        FileWriter myWriter = new FileWriter("output.txt");
        myWriter.write(mySolver.getSolution());
        myWriter.close();
    }
}
