import java.util.Scanner;
import java.util.Stack;

public class InfixToPostfix {

    static int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : infix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop(); // Pop '('
                } else {
                    return "Invalid infix expression"; // Unmatched parenthesis
                }
            } else {
                while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid infix expression"; // Unmatched parenthesis
            }
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an infix expression: ");
        String infixExpression = scanner.nextLine();

        String postfixExpression = infixToPostfix(infixExpression);

        if (postfixExpression.equals("Invalid infix expression")) {
            System.out.println("Invalid infix expression. Please check your input.");
        } else {
            System.out.println("Infix Expression: " + infixExpression);
            System.out.println("Postfix Expression: " + postfixExpression);
        }
    }
}
