package data.structure.queue;

/**
 * Created by Administrator on 2020/12/6
 */
public class CircleArrayQueue {

    public static void main(String[] args) {

    }


    // 循环数组 模拟队列

    private int maxSize;  // 数组最大容量
    private int front;    // 指向队列第一个元素   arr[front]  队列头
    private int rear;   // 指向队列的最后一个元素的后一个位置       //空出一个空间作为约定

    private int[] arr;  // 模拟队列  存放数据


    //创建队列构造器
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // 判断队列为空
    public boolean isEmpty() {
        return front == rear;
    }

    // 添加队列
    public void addQueue(int n) {  //rear 指向队尾后一个位置
        if (isFull()) {
            System.out.println("queue is full");
            return;
        }

        arr[rear] = n;
        rear = (rear + 1) % maxSize;  //队尾后移  考虑取模
    }

    // 获取数据 出队列
    public int popQueue() {  //front 指向队头
        if (isEmpty()) {
            throw new RuntimeException("队列为空 不能取数据");
        }


        int pop =  arr[front];
        front = (front + 1) % maxSize;
        return pop;
    }

    //当前队列有效数据个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }


    //显示队列所有数据
    public void showQueue() {   //当前有效个数  (rear + maxSize - front) % maxSize
        if (isEmpty()) {
            System.out.println("null");
            return;
        }

        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize , arr[i % maxSize]);
        }
    }


    // 显示队列头 不是取数据
    public int headQueue() {
        if (isEmpty()) {
            System.out.println("null");
            throw new RuntimeException("duilie null");
        }

        return arr[front];
    }


}
