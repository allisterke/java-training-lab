package leetcode.a742;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    static class TreeNodeWithParent {
        int val;
        TreeNodeWithParent left, right, parent;
        TreeNodeWithParent(int x) { val = x; }
    }

    public int findClosestLeaf(TreeNode root, int k) {
        return bfs(findTarget(clone(root), k)).val;
    }

    TreeNodeWithParent bfs(TreeNodeWithParent start) {
        HashSet<TreeNodeWithParent> map = new HashSet<>();
        Queue<TreeNodeWithParent> queue = new ArrayDeque<>();
        queue.add(start);
        map.add(start);
        while (!queue.isEmpty()) {
            TreeNodeWithParent front = queue.poll();
            if(front.left == null && front.right == null) {
                return front;
            }
            else {
                if(front.parent != null && !map.contains(front.parent)) {
                    map.add(front.parent);
                    queue.add(front.parent);
                }
                if(front.left != null && !map.contains(front.left)) {
                    map.add(front.left);
                    queue.add(front.left);
                }
                if(front.right != null && !map.contains(front.right)) {
                    map.add(front.right);
                    queue.add(front.right);
                }
            }
        }
        return null;
    }

    TreeNodeWithParent findTarget(TreeNodeWithParent node, int k) {
        if(node == null) {
            return null;
        }
        if(node.val == k) {
            return node;
        }
        TreeNodeWithParent target = findTarget(node.left, k);
        if(target != null) {
            return target;
        }
        else {
            return findTarget(node.right, k);
        }
    }

    TreeNodeWithParent clone(TreeNode node) {
        if(node == null) {
            return null;
        }
        else {
            TreeNodeWithParent np = new TreeNodeWithParent(node.val);
            np.left = clone(node.left);
            np.right = clone(node.right);
            if(np.left != null) {
                np.left.parent = np;
            }
            if(np.right != null) {
                np.right.parent = np;
            }
            return np;
        }
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