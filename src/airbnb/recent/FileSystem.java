package airbnb.recent;



import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class FileSystem {

    class Solutoin1 {

        Map<String, Integer> pathMap = new HashMap<>();
        Map<String, Runnable> callbackMap = new HashMap<>();


        public Solutoin1() {
            pathMap.put("",0);
        }


        public boolean create(String path, int value) {
            if (pathMap.containsKey(path))
                return false;
            int lastSlashIndex = path.lastIndexOf("/");
            String parent = path.substring(0, lastSlashIndex);
            if(pathMap.containsKey(parent)) {
                pathMap.put(path, value);
                return true;
            } else {
                return false;
            }
        }

        public Integer get(String path) {
            return pathMap.get(path);
        }

        public boolean set(String path, int value) {
            if (!pathMap.containsKey(path)) {
                return false;
            }
            pathMap.put(path, value);

            // Trigger callbacks
            String curPath = path;
            while (curPath.length() > 0) {
                System.out.println("curPath=" + curPath);
                if (callbackMap.containsKey(curPath)) {
                    callbackMap.get(curPath).run();
                }
                int lastSlashIndex = curPath.lastIndexOf("/");
                curPath = curPath.substring(0, lastSlashIndex);
            }

            return true;
        }


        public boolean watch(String path, Runnable callback) {
            if (!pathMap.containsKey(path)) {
                return false;
            }
            callbackMap.put(path, callback);
            return true;
        }

    } // class

    class Solutoin2 {
        class Node {
            String name;
            int value;
            Map<String, Node> children = new HashMap<>();
            Runnable callBack;

            public Node(String name, int value) {
                this.name = name;
                this.value = value;
            }

        }

        private Node root = new Node("", 0);

        public boolean create(String path, int value) {
            Node node = this.root;
            String[] names = path.split("/");
            for(int i=1; i<names.length-1; i++  ) {
                if(node.children.containsKey(names[i])) {
                    node = node.children.get(names[i]);
                } else {
                    return false;
                }
            }
            String name = names[names.length-1];
            if (node.children.containsKey(name)) {
                return false;
            }
            node.children.put(name, new Node(name, value));
            return true;
        }

        public Integer get(String path) {
            Node node = this.root;
            String[] names = path.split("/");
            for(int i=1; i<names.length; i++  ) {
                if(node.children.containsKey(names[i])) {
                    node = node.children.get(names[i]);
                } else {
                    return null;
                }
            }

            return node.value;
        }

        public boolean set(String path, int value) {
            Node node = this.root;
            String[] names = path.split("/");
            for(int i=1; i<names.length; i++  ) {
                if(node.children.containsKey(names[i])) {
                    node = node.children.get(names[i]);
                } else {
                    return false;
                }
            }
            node.value = value;
            return true;
        }
    } // class

    public static void main(String[] args) {
        FileSystem solution = new FileSystem();
        //    Solution.FileSystem1 sol =  solution.new FileSystem1();
        FileSystem.Solutoin2 sol =  solution.new Solutoin2();
        System.out.println("create /a:" + sol.create("/a",1));
        System.out.println("get /a :" + (int)sol.get("/a"));
        System.out.println("create /a/b:  " + sol.create("/a/b",2));
        System.out.println("get /a/b =" + (int)sol.get("/a/b"));
        System.out.println("set /a/b =" + sol.set("/a/b",3));
        System.out.println("get /a/b =" +(int)sol.get("/a/b"));
        System.out.println("create /c/d =" + sol.create("/c/d",4));
        System.out.println("set /c/d =" + sol.set("/c/d",4));

    }
}
