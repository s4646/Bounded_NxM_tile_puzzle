import java.util.Set;
import java.util.HashMap;
import java.util.Map.Entry;

public class Board {
    private Tile[][] board;
    private int[] emptyTileLocation;
    private HashMap<String, Integer> whiteTiles; // content, moves
    private int rows;
    private int cols;

    public Board(String b, String w)
    {
        this.rows = (int)(b.chars().filter(ch -> ch == '\n').count());
        this.cols = (int)(b.split("\n")[0].chars().filter(ch -> ch == ',').count()) + 1;
        // System.out.println("rows: "+rows+", cols: "+cols);
        this.board = new Tile[rows][cols];
        this.whiteTiles = getWhiteTiles(w);

        String[] rows_content = b.split("\n");
        for (int i = 0; i < rows; i++) {
            
            String[] content = rows_content[i].split(",");
            for (int j = 0; j < cols; j++) {
                if (whiteTiles.containsKey(content[j])) {
                    board[i][j] = new Tile(content[j], whiteTiles.get(content[j]));
                }
                else {
                    board[i][j] = new Tile(content[j]);
                }

                if (content[j].equals("_"))
                    emptyTileLocation = new int[] {i, j};
            }
        }
    }

    public HashMap<String, Integer> getWhiteTiles(String w)
    {
        HashMap<String, Integer> whiteTiles = new HashMap<String, Integer>();
        String[] rawTilesInput = w.split("\\(");

        for (int i = 1; i < rawTilesInput.length; i++) {
            
            String temp = rawTilesInput[i].split("\\)")[0];
            String content = temp.split(",")[0];
            int movesLeft = Integer.parseInt(temp.split(",")[1]);
            whiteTiles.put(content, movesLeft);
        }

        return whiteTiles;
    }

    public int[] getEmptyTileLocation()
    {
        return emptyTileLocation;
    }

    public Tile[][] getBoard()
    {
        return this.board;
    }

    public int[] getSize()
    {
        return new int[]{this.rows, this.cols};
    }

    public String whiteTilesToString()
    {
        String ret = " ";
        Set<Entry<String, Integer>> items = whiteTiles.entrySet();
        
        for (Entry<String, Integer> item : items) {
           ret += "("+item.getKey()+","+item.getValue()+"),"; 
        }
        return ret.substring(0, ret.length()-1);
    }

    @Override
    public String toString()
    {
        String ret = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                    ret += board[i][j].getContent();
                    if (j != cols-1) {
                        ret += ",";
                    }
            }
            ret += '\n';
        }
        return ret;
    }
}
