package DSAStackQueue.returnElementInArrayUseStack;

public class ReverseStringWithStack {
    public static void main(String[] args) {
        String input = "do u wait for me";
        String[] words = input.split(" ");
        java.util.Stack<String> stack = new java.util.Stack<>();
        for (String word : words) {
            stack.push(word);

        }
        StringBuilder output = new StringBuilder();
        while (!stack.isEmpty()){
            output.append(stack.pop()).append(" ");

        }
        System.out.println("Chuỗi đảo ngược: "+output.toString().trim());
    }
}
