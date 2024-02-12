import java.util.Set;
import java.util.HashMap;
import java.util.Map.Entry;

public class Board {
    private Tile[][] tiles;
    private int[] emptyTileLocation;
    private HashMap<String, Integer> whiteTiles; // content, moves
    private int rows;
    private int cols;

    public Board(String b, String w)
    {
        this.rows = (int)(b.chars().filter(ch -> ch == '\n').count());
        this.cols = (int)(b.split("\n")[0].chars().filter(ch -> ch == ',').count()) + 1;
        // System.out.println("rows: "+rows+", cols: "+cols);
        this.tiles = new Tile[rows][cols];
        this.whiteTiles = setWhiteTiles(w);

        String[] rows_content = b.split("\n");
        for (int i = 0; i < rows; i++) {
            
            String[] content = rows_content[i].split(",");
            for (int j = 0; j < cols; j++) {
                if (whiteTiles.containsKey(content[j])) {
                    tiles[i][j] = new Tile(content[j], whiteTiles.get(content[j]));
                }
                else {
                    tiles[i][j] = new Tile(content[j]);
                }

                if (content[j].equals("_"))
                    emptyTileLocation = new int[] {i, j};
            }
        }
    }

    public Board(Tile[][] b, int[] emptyloc, HashMap<String, Integer> wt) // clone
    {
        this.tiles = b;
        this.emptyTileLocation = emptyloc;
        this. whiteTiles = wt;
        this.rows = this.tiles.length;
        this.cols = this.tiles[0].length;
    }

    public HashMap<String, Integer> setWhiteTiles(String w)
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

    public Tile[][] getTiles()
    {
        return this.tiles;
    }

    public int[] getSize()
    {
        return new int[]{this.rows, this.cols};
    }

    public HashMap<String, Integer> getWhiteTiles()
    {
        return this.whiteTiles;
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

    @SuppressWarnings({"unchecked"})
    public Board clone()
    {
        Tile[][] clTiles = new Tile[this.tiles.length][];
        for (int i = 0; i < this.tiles.length; i++) {
            clTiles[i] = new Tile[this.tiles[i].length];
            for (int j = 0; j < this.tiles[i].length; j++) {
                clTiles[i][j] = tiles[i][j].clone();
            }
        }
        return new Board(clTiles, this.emptyTileLocation.clone(), (HashMap<String, Integer>)this.whiteTiles.clone());
    }

    @Override
    public String toString()
    {
        String ret = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                    ret += tiles[i][j].getContent();
                    if (j != cols-1) {
                        ret += ",";
                    }
            }
            ret += '\n';
        }
        return ret;
    }
}
