import java.util.Comparator;

public class NodeComparator implements Comparator<Node>{

    @Override
    public int compare(Node n1, Node n2)
    {
        return Integer.compare(n1.getCost() + ManhattenDistance(n1), n2.getCost() + ManhattenDistance(n2));
    }

    public int ManhattenDistance(Node n) // Heuristic Function
    {
        int dist = 0;
        int [] boardSize = n.getBoard().getSize();
        Tile[][] tiles = n.getBoard().getTiles();
        
        for (int i = 0; i < boardSize[0]; i++) {                 // rows -> Y
            for (int j = 0; j < boardSize[1]; j++) {             // cols -> X
                int value;
                if (tiles[i][j].getType() == 2) // Empty Tile
                    value = boardSize[0]*boardSize[1];
                else
                    value = Integer.parseInt(tiles[i][j].getContent());

                dist += Math.abs(i - (value-1) / boardSize[1]) + Math.abs(j - (value-1) % boardSize[1]);
            }
        }
        return dist;
    }
}
