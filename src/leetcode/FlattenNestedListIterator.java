package leetcode;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

interface NestedInteger {

  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  public boolean isInteger();

  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  public Integer getInteger();

  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return null if this NestedInteger holds a single integer
  public List<NestedInteger> getList();
}
 
public class FlattenNestedListIterator implements Iterator<Integer> {

    Stack<List<NestedInteger>> listStack = new Stack<>();
    Stack<Integer> pStack = new Stack<>();
    List<NestedInteger> nestedList;

    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        if (nestedList != null && nestedList.size()>0) {
            this.nestedList = nestedList;
            dfs(this.nestedList, 0);
        }
    }

    @Override
    public Integer next() {
        List<NestedInteger> list = listStack.peek();
        int p = pStack.peek();
        Integer res = list.get(p).getInteger();
        findNext();
        return res;
    }

    @Override
    public boolean hasNext() {
        return !listStack.empty();
    }

    private void findNext(){
        List<NestedInteger> list = null;
        int p=0;
        while(!listStack.empty()) {
            list = listStack.pop();
            p = pStack.pop();
            p++;
            if(dfs(list, p)) {
                return;
            }
        }
    }

    private boolean dfs(List<NestedInteger> list, int i) {
        listStack.push(list);
        for(;  i<list.size(); i++) {
            pStack.push(i);
            NestedInteger node = list.get(i);
            if (node.isInteger()) {
                return true;
            } else {
                boolean re = dfs(node.getList(), 0);
                if(!re) {
                    pStack.pop();
                } else {
                    return true;
                }
            }
        }
        listStack.pop();
        return false;
    }
}

 