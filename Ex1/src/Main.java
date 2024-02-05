public class Main {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");
        InputReader myReader = new InputReader();
        myReader.getInput("input.txt");
        System.out.println(myReader.toString());
        // System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Board myBoard = new Board(myReader.getBoard(), myReader.getWhiteTiles());
        System.out.println(myBoard.toString());
    }
}
