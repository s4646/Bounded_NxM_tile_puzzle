import java.util.Stack;
import java.util.Queue;
import java.util.HashMap;
import java.util.LinkedList;

public class Algorithms {

    public static boolean isGoal(Node current, Node goal)
    {
        return current.getBoard().toString().equals(goal.getBoard().toString());
    }

    public static LinkedList<Character> getValidOperators(Node n)
    {
        int[] boardSize = n.getBoard().getSize();
        int[] emptyPos = n.getBoard().getEmptyTileLocation();
        LinkedList<Character> operators = new LinkedList<Character>();

        if (emptyPos[1] != 0)               // Left
            operators.add('L');         
        if (emptyPos[0] != 0)               // Up
            operators.add('U');         
        if (emptyPos[1] != boardSize[1]-1)  // Right
            operators.add('R');         
        if (emptyPos[0] != boardSize[0]-1)  // Down
            operators.add('D');         

        return operators;

    }

    public static String goalString(int[] size)
    {
        int rows = size[0];
        int cols = size[1];
        String b = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((i+1)*rows + j == (rows*cols)) {
                    b += "_";
                }
                else {
                    b += (i*rows + i + j + 1);
                }

                if (j != cols-1) {
                    b += ",";
                }
            }
            b += '\n';
        }
        return b;
    }

    public static Node operate(Node n, char operator)
    {
        Node kid = new Node(n.getBoard().clone());
        Tile[][] board = kid.getBoard().getTiles();
        int[] emptyloc = kid.getBoard().getEmptyTileLocation();
        int[] swaploc = new int[2];
        Tile temp = null;

        switch (operator) {
            case 'L':
                swaploc[0] = emptyloc[0];
                swaploc[1] = emptyloc[1]-1;
                temp = board[swaploc[0]][swaploc[1]].clone();
                
                // white tile
                if (temp.getType() == 1) {
                    if (temp.getMovesLeft() > 0)
                        kid.getBoard().getWhiteTiles().replace(temp.getContent(), temp.getMovesLeft() - 1);
                    else
                        return null;
                }
                
                // swap tiles
                board[swaploc[0]][swaploc[1]] = board[emptyloc[0]][emptyloc[1]];
                board[emptyloc[0]][emptyloc[1]] = temp;
                emptyloc[0] = swaploc[0];
                emptyloc[1] = swaploc[1];
                break;
            
            case 'U':
                swaploc[0] = emptyloc[0]-1;
                swaploc[1] = emptyloc[1];
                temp = board[swaploc[0]][swaploc[1]].clone();
                
                // white tile
                if (temp.getType() == 1) {
                    if (temp.getMovesLeft() > 0)
                        kid.getBoard().getWhiteTiles().replace(temp.getContent(), temp.getMovesLeft() - 1);
                    else
                        return null;
                }
                
                // swap tiles
                board[swaploc[0]][swaploc[1]] = board[emptyloc[0]][emptyloc[1]];
                board[emptyloc[0]][emptyloc[1]] = temp;
                emptyloc[0] = swaploc[0];
                emptyloc[1] = swaploc[1];
                break;

            case 'R':
                swaploc[0] = emptyloc[0];
                swaploc[1] = emptyloc[1]+1;
                temp = board[swaploc[0]][swaploc[1]].clone();
                
                // white tile
                if (temp.getType() == 1) {
                    if (temp.getMovesLeft() > 0)
                        kid.getBoard().getWhiteTiles().replace(temp.getContent(), temp.getMovesLeft() - 1);
                    else
                        return null;
                }
                
                // swap tiles
                board[swaploc[0]][swaploc[1]] = board[emptyloc[0]][emptyloc[1]];
                board[emptyloc[0]][emptyloc[1]] = temp;
                emptyloc[0] = swaploc[0];
                emptyloc[1] = swaploc[1];
                break;

            case 'D':
                swaploc[0] = emptyloc[0]+1;
                swaploc[1] = emptyloc[1];
                temp = board[swaploc[0]][swaploc[1]].clone();
                
                // white tile
                if (temp.getType() == 1) {
                    if (temp.getMovesLeft() > 0)
                        kid.getBoard().getWhiteTiles().replace(temp.getContent(), temp.getMovesLeft() - 1);
                    else
                        return null;
                }
                
                // swap tiles
                board[swaploc[0]][swaploc[1]] = board[emptyloc[0]][emptyloc[1]];
                board[emptyloc[0]][emptyloc[1]] = temp;
                emptyloc[0] = swaploc[0];
                emptyloc[1] = swaploc[1];
                break;
        
            default:
                break;
        }

        return kid;
    }

    public static String dfid(Node start, Node goal)
    {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            HashMap<String, Node> H = new HashMap<String, Node>();
            String result = limitedDfs(start, goal, 0, H, "");
            if (!result.equals("cutoff")) {
                return result;
            }
        }
        return "";
    }

    public static String limitedDfs(Node n, Node goal, int limit, HashMap<String, Node> h, String path)
    {
        // TODO
        if (isGoal(n, goal)) return path;
        if (limit == 0) return "cutoff";
        
        h.put(n.getBoard().toString(), n);
        String isCutoff = "false";

        LinkedList<Character> ops = getValidOperators(n);
        n.createNextArray(ops.size());
        for (Character op : ops) {
            
        }

        return path;
    }
}
