package learn;

import java.util.LinkedList;

public class LearnLinkedList {

    private static void initLinkedList(LinkedList list, int n) {
        for(int i=0; i<n; i++) {
            list.add(i+"s");
        }
    }

    private static void printLinkedList(LinkedList list) {
        for(int i=0; i<list.size(); i++) {
            System.out.print(list.get(i) + ", ");
        }
        System.out.println();
    }

    private static void removeFromLinkedList(LinkedList list, int n) {
        list.remove(n+"su");
    }

    public static void main(String[] args){
        LinkedList<String> list = new LinkedList<>();
        initLinkedList(list, 10);
        printLinkedList(list);
        removeFromLinkedList(list, 3);
        printLinkedList(list);
    }
}
