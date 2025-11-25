package youyube;

public class MyArrayStack extends isstackqueue {
    private final int[] array;
    private final int size;
    private int topIndex;

    MyArrayStack(int Size) {
        size = Size;
        array = new int[size];
        topIndex = -1;
    }

    @Override
    public boolean isEmpty() {
        return topIndex == -1;
    }

    @Override
    public boolean isFull() {
        return topIndex == size - 1;
    }
    @Override
    public boolean push(int value) {
        if (!isFull()) {
            topIndex++;
            array[topIndex] = value;
            return true;
        }
        return false;
    }
    @Override
    public int pop(){
        if (!isEmpty()){
            int value = array[topIndex];
            topIndex--;
            return value;
        }else
            return -1;
    }
    @Override
    public void show(){
        if(isEmpty()){
            System.out.println("Stack is empty");
        }else
            for (int i = 0;i <= topIndex;i++){
                System.out.print(array[i]+" ");
            }
        System.out.println();
    }

}
