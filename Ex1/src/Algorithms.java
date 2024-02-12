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

        if (emptyPos[1] != 0)
            operators.add('L');         // Left
        if (emptyPos[0] != 1)
            operators.add('U');         // Up
        if (emptyPos[1] != boardSize[1])
            operators.add('R');         // Right
        if (emptyPos[0] != boardSize[0])
            operators.add('D');         // Down

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
