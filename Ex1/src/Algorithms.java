import java.util.Stack;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Algorithms {

    public static boolean isGoal(Node current, Node goal)
    {
        return current.toString().equals(goal.toString());
    }

    public static LinkedList<Character> getValidOperators(Node n)
    {
        // Valid operations for the EMPTY TILE, for red/white tiles reverse operators, meaning 'L' -> 'R' etc.
        int[] boardSize = n.getBoard().getSize();
        int[] emptyPos = n.getBoard().getEmptyTileLocation();
        LinkedList<Character> operators = new LinkedList<Character>();

        /* CORRECT ORDER FOR RED/WHITE TILES */
        if (emptyPos[1] != boardSize[1]-1)  // Right
            operators.add('R'); 
        if (emptyPos[0] != boardSize[0]-1)  // Down
            operators.add('D');
        if (emptyPos[1] != 0)               // Left
            operators.add('L');
        if (emptyPos[0] != 0)               // Up
            operators.add('U');
        
        /* ORDER FOR EMPTY TILE
        if (emptyPos[1] != 0)               // Left
            operators.add('L');         
        if (emptyPos[0] != 0)               // Up
            operators.add('U');         
        if (emptyPos[1] != boardSize[1]-1)  // Right
            operators.add('R');         
        if (emptyPos[0] != boardSize[0]-1)  // Down
            operators.add('D');
        */      

        return operators;

    }

    public static String goalString(int[] size)
    {
        int rows = size[0];
        int cols = size[1];
        String b = "";
        int counter = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((i == rows-1 && j == cols-1)) {
                    b += "_";
                }
                else {
                    b += counter++;
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
        Tile[][] tiles = kid.getBoard().getTiles();
        int[] emptyloc = kid.getBoard().getEmptyTileLocation();
        int[] swaploc = new int[2];
        Tile temp = null;

        switch (operator) {
            case 'L':
                swaploc[0] = emptyloc[0];
                swaploc[1] = emptyloc[1]-1;
                temp = tiles[swaploc[0]][swaploc[1]].clone();
                
                // white tile
                if (temp.getType() == 1) {
                    if (temp.getMovesLeft() > 0) {
                        kid.getBoard().getWhiteTiles().replace(temp.getContent(), temp.getMovesLeft() - 1);
                        kid.setWhiteTileMoved(true);
                        kid.setCost(n.getCost()+1);
                    }
                    else
                        return null;
                }
                else kid.setCost(n.getCost()+30);
                
                // swap tiles
                tiles[swaploc[0]][swaploc[1]] = tiles[emptyloc[0]][emptyloc[1]];
                tiles[emptyloc[0]][emptyloc[1]] = temp;
                emptyloc[0] = swaploc[0];
                emptyloc[1] = swaploc[1];
                break;
            
            case 'U':
                swaploc[0] = emptyloc[0]-1;
                swaploc[1] = emptyloc[1];
                temp = tiles[swaploc[0]][swaploc[1]].clone();
                
                // white tile
                if (temp.getType() == 1) {
                    if (temp.getMovesLeft() > 0) {
                        kid.getBoard().getWhiteTiles().replace(temp.getContent(), temp.getMovesLeft() - 1);
                        kid.setWhiteTileMoved(true);
                        kid.setCost(n.getCost()+1);
                    }
                    else
                        return null;
                }
                else kid.setCost(n.getCost()+30);
                
                // swap tiles
                tiles[swaploc[0]][swaploc[1]] = tiles[emptyloc[0]][emptyloc[1]];
                tiles[emptyloc[0]][emptyloc[1]] = temp;
                emptyloc[0] = swaploc[0];
                emptyloc[1] = swaploc[1];
                break;

            case 'R':
                swaploc[0] = emptyloc[0];
                swaploc[1] = emptyloc[1]+1;
                temp = tiles[swaploc[0]][swaploc[1]].clone();
                
                // white tile
                if (temp.getType() == 1) {
                    if (temp.getMovesLeft() > 0) {
                        kid.getBoard().getWhiteTiles().replace(temp.getContent(), temp.getMovesLeft() - 1);
                        kid.setWhiteTileMoved(true);
                        kid.setCost(n.getCost()+1);
                    }
                    else
                        return null;
                }
                else kid.setCost(n.getCost()+30);
                
                // swap tiles
                tiles[swaploc[0]][swaploc[1]] = tiles[emptyloc[0]][emptyloc[1]];
                tiles[emptyloc[0]][emptyloc[1]] = temp;
                emptyloc[0] = swaploc[0];
                emptyloc[1] = swaploc[1];
                break;

            case 'D':
                swaploc[0] = emptyloc[0]+1;
                swaploc[1] = emptyloc[1];
                temp = tiles[swaploc[0]][swaploc[1]].clone();
                
                // white tile
                if (temp.getType() == 1) {
                    if (temp.getMovesLeft() > 0) {
                        kid.getBoard().getWhiteTiles().replace(temp.getContent(), temp.getMovesLeft() - 1);
                        kid.setWhiteTileMoved(true);
                        kid.setCost(n.getCost()+1);
                    }
                    else
                        return null;
                }
                else kid.setCost(n.getCost()+30);
                
                // swap tiles
                tiles[swaploc[0]][swaploc[1]] = tiles[emptyloc[0]][emptyloc[1]];
                tiles[emptyloc[0]][emptyloc[1]] = temp;
                emptyloc[0] = swaploc[0];
                emptyloc[1] = swaploc[1];
                break;
        
            default:
                break;
        }

        return kid;
    }
    
    public static String getPath(Node n)
    {
        String path = "";
        Node pointer = n;
        Stack<Node> nodeStack = new Stack<Node>();
        LinkedList<Node> nodePath = new LinkedList<Node>();
        
        while (pointer != null) {
            nodeStack.add(pointer);
            pointer = pointer.getPrev();
        }
        while (!nodeStack.isEmpty()) nodePath.add(nodeStack.pop());
        
        int listSize = nodePath.size();
        for (int i = 0; i < listSize-1; i++) {
            Node curr = nodePath.get(i);
            Node next = nodePath.get(i+1);
            int[] currEmptyLoc = curr.getBoard().getEmptyTileLocation();
            int[] nextEmptyLoc = next.getBoard().getEmptyTileLocation();

            if (nextEmptyLoc[0]-currEmptyLoc[0] == 1)
                path += curr.getBoard().getTiles()[nextEmptyLoc[0]][nextEmptyLoc[1]].getContent()+"U";
            else if (nextEmptyLoc[0]-currEmptyLoc[0] == -1)
                path += curr.getBoard().getTiles()[nextEmptyLoc[0]][nextEmptyLoc[1]].getContent()+"D";
            else if (nextEmptyLoc[1]-currEmptyLoc[1] == 1)
                path += curr.getBoard().getTiles()[nextEmptyLoc[0]][nextEmptyLoc[1]].getContent()+"L";
            else if (nextEmptyLoc[1]-currEmptyLoc[1] == -1)
                path += curr.getBoard().getTiles()[nextEmptyLoc[0]][nextEmptyLoc[1]].getContent()+"R";
            if (i != listSize-2) path+="-";
        }

        return path;
    }
    
    public static String DFID(Node start, Node goal)
    {
        AtomicInteger numOfNodes = new AtomicInteger();
        for (int limit = 0; limit < Integer.MAX_VALUE; limit++) {
            HashMap<String, Node> H = new HashMap<String, Node>();
            String result = limited_DFS(start, goal, limit, H, numOfNodes);
            if (!result.equals("cutoff")) return result;
        }
        return "";
    }

    public static String limited_DFS(Node n, Node goal, int limit, HashMap<String,Node> H, AtomicInteger numOfNodes)
    {
        if (isGoal(n, goal))
            return getPath(n)+","+numOfNodes.get()+","+n.getCost();
        else if (limit == 0) return "cutoff";
        else {
            H.put(n.toString(), n);
            boolean isCutOff = false;
            
            LinkedList<Character> operators = getValidOperators(n);
            for (Character operator : operators) {
                Node g = operate(n, operator);
                if (g == null) continue;
                n.addNext(g);
                g.setPrev(n);
                if (H.containsKey(g.toString())) continue;
                H.put(g.toString(), g);
                numOfNodes.getAndIncrement();
                String result = limited_DFS(g, goal, limit-1, H, numOfNodes);
                if (result.equals("cutoff")) isCutOff = true;
                else if (!result.equals("fail")) return result;
            }

            H.remove(n.toString());
            if (isCutOff == true) return "cutoff";
            else return "fail";
        }
    }

    public static String A_Star(Node start, Node goal, boolean printOpenList)
    {
        int numOfNodes = 0;
        NodePriorityQueue L = new NodePriorityQueue();
        HashMap<String, Node> L_table = new HashMap<String, Node>();
        L.add(start);
        L_table.put(start.toString(), start);
        HashMap<String, Node> C = new HashMap<String, Node>();
        
        while (!L.isEmpty()) {
            if (printOpenList) printOpenList(L_table);
            Node n = L.poll();
            if (isGoal(n, goal))
                return getPath(n)+","+numOfNodes+","+n.getCost();
            C.put(n.toString(), n);
            
            LinkedList<Character> operators = getValidOperators(n);
            for (Character operator : operators) {
                Node x = operate(n, operator);
                if (x == null) continue;
                n.addNext(x);
                x.setPrev(n);
                if (!C.containsKey(x.toString()) && !L_table.containsKey(x.toString())) {
                    L.add(x);
                    L_table.put(x.toString(), x);
                }
                else if(L_table.get(x.toString()).compareTo(x) == 1) {
                    L.remove(L_table.remove(x.toString()));
                    L.add(L_table.put(x.toString(), x));
                }
                
                numOfNodes++;
            }
        }
    return "false";
    }

    public static void printOpenList(HashMap<String,Node> hm)
    {
        System.out.println("Open List Size: "+hm.size());
        int counter = 0;

        for(Map.Entry<String, Node> entry : hm.entrySet()) {
            String key = entry.getKey();
            // Node value = entry.getValue();
            System.out.println("Item no. "+(counter++));
            System.out.println(key);
        }
    }
}
