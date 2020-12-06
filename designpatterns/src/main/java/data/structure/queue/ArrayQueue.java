package data.structure.queue;

/**
 * Created by Administrator on 2020/12/6
 */
public class ArrayQueue {

    public static void main(String[] args) {

        ArrayQueue arrayQueue = new ArrayQueue(3);

    }


    // 数组模拟队列

    private int maxSize;  //数组最大容量
    private int front;    // 指向队列头部   前一个位置
    private int rear;   //队列尾部

    private int[] arr;  //模拟队列  存放数据


    //创建队列构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = rear = -1;
    }

    //判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 判断队列为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("queue is full");
            return;
        }

        rear++;  //队尾后移
        arr[rear] = n;
    }

    // 获取数据 出队列
    public int popQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空 不能取数据");
        }

        front++;
        return arr[front];
    }


    //显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("null");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n", i , arr[i]);
        }
    }


    // 显示队列头 不是取数据
    public int headQueue() {
        if (isEmpty()) {
            System.out.println("null");
            throw new RuntimeException("duilie null");
        }

        return arr[front + 1];
    }


}
