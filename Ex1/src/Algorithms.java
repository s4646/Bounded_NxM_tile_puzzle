import java.util.Stack;
import java.util.Queue;
import java.util.HashMap;

public class Algorithms {

    public static boolean compareCurrentToGoal(Node current, Node goal)
    {
        return current.getBoard().toString().equals(goal.getBoard().toString());
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
        return path;
    }
}
