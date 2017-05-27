package leetcode.a133;

import java.util.*;

class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}

public class Solution {
    Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) {
            return null;
        }
        UndirectedGraphNode cloned = map.get(node);
        if(cloned == null) {
            cloned = new UndirectedGraphNode(node.label);
            map.put(node, cloned);
            for(UndirectedGraphNode neighbor : node.neighbors) {
                cloned.neighbors.add(cloneGraph(neighbor));
            }
        }
        return cloned;
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}