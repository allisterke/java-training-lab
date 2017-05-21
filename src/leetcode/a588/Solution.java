package leetcode.a588;

import java.util.*;

class Node {
    String name;
    public Node(String name) {
        this.name = name;
    }
}

class Directory extends Node {
    Map<String, Node> children = new TreeMap<>();
    public Directory(String name) {
        super(name);
    }
}

class File extends Node {
    StringBuilder sb = new StringBuilder();
    public File(String name) {
        super(name);
    }
}

class FileSystem {
    Directory root = new Directory("/");

    public FileSystem() {

    }

    public List<String> ls(String path) {
        String[] paths = path.split("/");
        Node last = root;
        for(String p : paths) {
            if(p.isEmpty()) {
                continue;
            }
            last = ((Directory)last).children.get(p);
        }
        List<String> list = new ArrayList<>();
        if(last instanceof Directory) {
            list.addAll(((Directory)last).children.keySet());
        }
        else {
            list.add(last.name);
        }
        return list;
    }

    public void mkdir(String path) {
        String[] paths = path.split("/");
        Directory last = root;
        for(String p : paths) {
            if(p.isEmpty()) {
                continue;
            }
            if(!last.children.containsKey(p)) {
                last.children.put(p, new Directory(p));
            }
            last = (Directory)last.children.get(p);
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] paths = filePath.split("/");
        Directory last = root;
        for(int i = 0; i < paths.length - 1; ++ i) {
            String p = paths[i];
            if(p.isEmpty()) {
                continue;
            }
            if(!last.children.containsKey(p)) {
                last.children.put(p, new Directory(p));
            }
            last = (Directory)last.children.get(p);
        }
        String fileName = paths[paths.length - 1];
        if(!last.children.containsKey(fileName)) {
            last.children.put(fileName, new File(fileName));
        }
        File file = (File)last.children.get(fileName);
        file.sb.append(content);
    }

    public String readContentFromFile(String filePath) {
        String[] paths = filePath.split("/");
        Node last = root;
        for(String p : paths) {
            if(p.isEmpty()) {
                continue;
            }
            last = ((Directory)last).children.get(p);
        }
        return ((File)last).sb.toString();
    }
}

public class Solution {


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