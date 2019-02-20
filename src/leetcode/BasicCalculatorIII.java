package leetcode;

import java.util.Stack;

public class BasicCalculatorIII {

    class Pair  {
        int res;
        int pos;
        public Pair(int res,int pos) {
            this.res = res;
            this.pos = pos;
        }
    }

    public int calculate(String s) {
        return calculate(s,0).res;
    }

    private Pair calculate(String s, int i) {
        Stack stack = new Stack();

        while( i<s.length() && s.charAt(i) != ')') {
            if (  s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) >='0' && s.charAt(i) <='9') {
                int num = 0;
                while(i<s.length() && (s.charAt(i) >='0' && s.charAt(i) <='9') ) {
                    num = num * 10 +  (s.charAt(i) - '0');
                    i++;
                }
                i--;
                pushNum(stack,  num);
            }  else if (s.charAt(i) == '(') {
                Pair p = calculate( s, ++i ) ;
                pushNum(stack,  p.res);
                i = p.pos;
            }
            i++;
        }
        int j = 0;
        int num = (Integer) stack.get(j++);
        while(j<stack.size()) {
            Character op = (Character)stack.get(j++);
            Integer right = (Integer) stack.get(j++);
            num = (op == '+') ? num + right : num - right;
        }
        return new Pair(num, i++);
    }

    private void pushNum(Stack stack, int num) {
        if (stack.isEmpty()) {
            stack.push(num);
        } else {
            Character top = (Character)stack.peek();
            if (top == '+' || top == '-') {
                stack.push(num);
            } else {
                Character op = (Character)stack.pop();
                Integer left = (Integer) stack.pop();
                stack.push(op == '*' ? left * num : left / num);
            }
        }
    }

    public static void main(String[] args) {
        BasicCalculatorIII solution = new BasicCalculatorIII();
        int res = solution.calculate("2*(5+5*2)/3+(6/2+8)");
        System.out.println(res);
    }
}
