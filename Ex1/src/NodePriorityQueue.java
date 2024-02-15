import java.util.Comparator;
import java.util.PriorityQueue;

public class NodePriorityQueue extends PriorityQueue<Node> implements Comparator<Node>{
        
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Node)) {
            return false;
        }
        Node n = (Node) obj;
        return this.toString().equals(n.toString());
    }

    @Override
    public int compare(Node n1, Node n2)
    {
        return n1.compareTo(n2);
       // return Integer.compare(n1.getCost(), n2.getCost());
    }

    // public Node get(Node n)
    // {
    //     for (Node item : this) {
    //         if (n.toString().equals(item.toString()))
    //             return item;
    //     }
    //     return null;
    // }
}
