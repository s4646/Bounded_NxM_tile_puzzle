import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputReader {
    
    private String algorithm;
    private String withTime;
    private String printOpenList;
    private String boardFormat;
    private String whiteTiles;
    private String board;

    public InputReader()
    {
        this.board = "";
    }

    public void getInput(String inputPath) throws FileNotFoundException
    {
        Scanner inputFile = new Scanner(new File(inputPath));
        this.algorithm = inputFile.nextLine();
        this.withTime = inputFile.nextLine();
        this.printOpenList = inputFile.nextLine();
        this.boardFormat = inputFile.nextLine();
        this.whiteTiles = inputFile.nextLine();
        while (inputFile.hasNextLine()) {
            board += inputFile.nextLine()+'\n';
        }
        inputFile.close();
    }

    public String getBoard()
    {
        return this.board;
    }

    @Override
    public String toString()
    {
        String ret = this.algorithm+'\n';
        ret += this.withTime+'\n';
        ret += this.printOpenList+'\n';
        ret += this.boardFormat+'\n';
        ret += this.whiteTiles+'\n';
        ret += this.board;
        return ret;
    }
}
