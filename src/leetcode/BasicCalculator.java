package leetcode;

import java.util.Stack;

public class BasicCalculator {
    public int calculate(String s) {
        Stack stack = new Stack();

        for(int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '('  || s.charAt(i) == '+' || s.charAt(i) == '-') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) >='0' && s.charAt(i) <='9') {
                int num = 0;
                while(i<s.length() && (s.charAt(i) >='0' && s.charAt(i) <='9') ) {
                    num = num * 10 +  (s.charAt(i) - '0');
                    i++;
                }
                i--;
                pushNum(stack,  num);
            } else if (s.charAt(i) == ')') {
                Integer num = (Integer) stack.pop();
                stack.pop();
                pushNum(stack,  num);
            }
        }
        return (Integer)stack.pop();

    }

    private void pushNum(Stack stack, int num) {
        if (stack.isEmpty()) {
            stack.push(num);
        } else {
            Character top = (Character)stack.peek();
            if (top == '(') {
                stack.push(num);
            } else {
                Character op = (Character)stack.pop();
                Integer left = (Integer) stack.pop();
                stack.push(op == '+' ? left + num : left - num);
            }
        }
    }

    public static void main(String[] args) {
        BasicCalculator solution = new BasicCalculator();
        int res = solution.calculate("(1+(4+5+2)-3)+(6+8)");
        System.out.println(res);
    }
}
