package DSAStackQueue.returnElementInArrayUseStack;

public class ReverseArrayWithStack {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6};
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        for (int num : arr) {
            stack.push(num);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = stack.pop();
        }
        System.out.print("mảng đảo ngược: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
