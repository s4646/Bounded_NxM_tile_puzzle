import java.util.Stack;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * DFID, A*, IDA* and DFBnB are as similar as possible to the pseudo code shown in lectures
 * Specifically L2.pdf and L3.pdf
 * https://drive.google.com/drive/folders/1EI486BhRIwRlc5qozl4J-fqC8RhxX3Ko
 */
public class Algorithms {

    public static int factorial(int n)
    {
        int fact = 1;    
        for(int i=1;i <= n; i++)   
            fact=fact*i;
        
            return fact;
    }

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
        Board b = n.getBoard().clone();
        Tile[][] tiles = b.getTiles();
        int[] emptyloc = b.getEmptyTileLocation();
        int[] swaploc = new int[2];
        Tile temp = null;
        int cost = 0;
        boolean isWhiteTileMoved = false;

        switch (operator) {
            case 'L':
                swaploc[0] = emptyloc[0];
                swaploc[1] = emptyloc[1]-1;
                temp = tiles[swaploc[0]][swaploc[1]].clone();
                
                // white tile
                if (temp.getType() == 1) {
                    if (temp.getMovesLeft() > 0) {
                        temp.setMoves(temp.getMovesLeft() - 1);
                        b.getWhiteTiles().replace(temp.getContent(), temp.getMovesLeft());
                        isWhiteTileMoved = true;
                        cost = n.getCost()+1;
                    }
                    else
                        return null;
                }
                else cost = n.getCost()+30;
                
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
                        temp.setMoves(temp.getMovesLeft() - 1);
                        b.getWhiteTiles().replace(temp.getContent(), temp.getMovesLeft());
                        isWhiteTileMoved = true;
                        cost = n.getCost()+1;
                    }
                    else
                        return null;
                }
                else cost = n.getCost()+30;
                
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
                        temp.setMoves(temp.getMovesLeft() - 1);
                        b.getWhiteTiles().replace(temp.getContent(), temp.getMovesLeft());
                        isWhiteTileMoved = true;
                        cost = n.getCost()+1;
                    }
                    else
                        return null;
                }
                else cost = n.getCost()+30;
                
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
                        temp.setMoves(temp.getMovesLeft() - 1);
                        b.getWhiteTiles().replace(temp.getContent(), temp.getMovesLeft());
                        isWhiteTileMoved = true;
                        cost = n.getCost()+1;
                    }
                    else
                        return null;
                }
                else cost = n.getCost()+30;
                
                // swap tiles
                tiles[swaploc[0]][swaploc[1]] = tiles[emptyloc[0]][emptyloc[1]];
                tiles[emptyloc[0]][emptyloc[1]] = temp;
                emptyloc[0] = swaploc[0];
                emptyloc[1] = swaploc[1];
                break;
        
            default:
                break;
        }

        // check whether this is a "reverse" operator
        // if the next node takes us back to the father node, dont create it 
        if (n.getPrev() != null && b.toString().equals(n.getPrev().toString()))
            return null;
        else {
            Node kid = new Node(b);
            kid.setWhiteTileMoved(isWhiteTileMoved);
            kid.setCost(cost);
            return kid;
        }
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

            // given two nodes, determine the operator by the empty tile's location
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
    
    public static String DFID(Node start, Node goal, boolean printOpenList)
    {
        AtomicInteger numOfNodes = new AtomicInteger();
        for (int limit = 1; limit < Integer.MAX_VALUE; limit++) {
            HashMap<String, Node> H = new HashMap<String, Node>();
            String result = limited_DFS(start, goal, limit, H, numOfNodes, printOpenList);
            if (!result.equals("cutoff")) return result;
        }
        return "";
    }

    public static String limited_DFS(Node n, Node goal, int limit, HashMap<String,Node> H, AtomicInteger numOfNodes, boolean printOpenList)
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
                numOfNodes.getAndIncrement();
                n.addNext(g);
                g.setPrev(n);
                if (H.containsKey(g.toString())) continue;
                H.put(g.toString(), g);
                if (printOpenList) printOpenList(H);
                String result = limited_DFS(g, goal, limit-1, H, numOfNodes, printOpenList);
                if (result.equals("cutoff")) isCutOff = true;
                else if (!result.equals("no path")) return result;
            }

            H.remove(n.toString());
            if (isCutOff == true) return "cutoff";
            else return "no path";
        }
    }

    public static String A_Star(Node start, Node goal, boolean printOpenList)
    {
        int numOfNodes = 0;
        NodeComparator cmp = new NodeComparator();
        PriorityQueue<Node> L = new PriorityQueue<>(cmp);
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
                else if(cmp.compare(L_table.get(x.toString()), x) == 1) {
                    L.remove(L_table.remove(x.toString()));
                    L.add(x);
                    L_table.put(x.toString(), x);
                }
                
                numOfNodes++;
            }
        }
    return "no path";
    }

    public static String IDA_Star(Node start, Node goal, boolean printOpenList)
    {
        Stack<Node> L = new Stack<Node>();
        NodeComparator cmp = new NodeComparator();
        HashMap<String, Node> H = new HashMap<String, Node>();
        int t = cmp.ManhattenDistance(start);
        int minF;
        int numOfNodes = 0;
        while (t != Integer.MAX_VALUE) {
            start.setOut(false);
            minF = Integer.MAX_VALUE;
            L.push(start);
            H.put(start.toString(), start);
            
            while (!L.isEmpty()) {
                if (printOpenList) printOpenList(H);
                Node n = L.pop();
                if (n.isOut())
                    H.remove(n.toString());
                else {
                    n.setOut(true);
                    L.push(n);

                    LinkedList<Character> operators = getValidOperators(n);
                    for (Character operator : operators) {
                        Node g = operate(n, operator);
                        if (g == null) continue;
                        numOfNodes++;
                        n.addNext(g);
                        g.setPrev(n);

                        int Fg = g.getCost() + cmp.ManhattenDistance(g);
                        if (Fg > t) {
                            minF = Integer.min(minF, Fg);
                            continue;
                        }
                        if (H.containsKey(g.toString()) && H.get(g.toString()).isOut())
                            continue;
                        if (H.containsKey(g.toString()) && !H.get(g.toString()).isOut()) {
                            Node g_ = H.get(g.toString());
                            if (g_.getCost() + cmp.ManhattenDistance(g_) > g.getCost() + cmp.ManhattenDistance(g))
                                L.remove(H.remove(g_.toString()));
                            else
                                continue;
                        }
                        if (isGoal(g, goal))
                            return getPath(g)+","+numOfNodes+","+g.getCost();
                        L.push(g);
                        H.put(g.toString(), g);
                    }
                }    
            }
            t = minF;
        }
        return "no path";
    }

    public static String DFBnB(Node start, Node goal, boolean printOpenList)
    {
        NodeComparator cmp = new NodeComparator();
        Stack<Node> L = new Stack<Node>();
        HashMap<String, Node> H = new HashMap<String, Node>();
        L.push(start);
        H.put(start.toString(), start);
        String result = "no path";
        int[] boardSize = start.getBoard().getSize();
        int t = Integer.min(Integer.MAX_VALUE, factorial(boardSize[0]*boardSize[1]-1));
        int numOfNodes = 0;

        while (!L.isEmpty()) {
            if (printOpenList) printOpenList(H);
            Node n = L.pop();
            if (n.isOut())
                H.remove(n.toString());
            else {
                n.setOut(true);
                L.push(n);

                LinkedList<Character> operators = getValidOperators(n);
                LinkedList<Node> N = new LinkedList<Node>();
                for (Character operator : operators) {
                    Node temp = operate(n,operator);
                    if (temp != null) {
                        n.addNext(temp);
                        temp.setPrev(n);
                        numOfNodes++;
                        N.add(temp);
                    }
                }
                Collections.sort(N, cmp);
                
                for (int i = 0; i < N.size(); i++) {
                    Node g = N.get(i);
                    if (g.getCost() + cmp.ManhattenDistance(g) >= t) {
                        for (int j = i; j < N.size(); j++) {
                            Node temp = N.get(j);
                                N.remove(temp);
                                j--;
                        }
                    }
                    else if (H.containsKey(g.toString()) && g.isOut())
                        N.remove(g);
                    else if (H.containsKey(g.toString()) && !g.isOut()) {
                        Node g_ = H.get(g.toString());
                        if (g_.getCost() + cmp.ManhattenDistance(g_) <= g.getCost() + cmp.ManhattenDistance(g))
                            N.remove(g);
                        else
                            L.remove(H.remove(g_.toString()));
                    }
                    else if (isGoal(g, goal)) {
                        t = g.getCost() + cmp.ManhattenDistance(g);
                        result = getPath(g)+","+numOfNodes+","+g.getCost();
                        
                        for (int j = i; j < N.size(); j++) {
                            Node temp = N.get(j);
                                N.remove(temp);
                                j--;
                        }
                    }
                }
                Collections.reverse(N);
                for (int i = 0; i < N.size(); i++) {
                    Node k = N.get(i);
                    L.push(k);
                    H.put(k.toString(), k);
                }
            }
        }

        if (result.equals("no path")) return result;
        else {
            String[] res = result.split(",");
            result = res[0]+","+numOfNodes+","+res[2];
            return result;
        }
    }
}
