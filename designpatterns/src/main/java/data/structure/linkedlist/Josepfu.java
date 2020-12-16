package data.structure.linkedlist;

/**
 * author: Andy
 * 2020/12/15
 * Describe:  约瑟夫问题  单向环形链表
 */
public class Josepfu {

    public static void main(String[] args) {
        CircleSimpleLinkedList circleSimpleLinkedList = new CircleSimpleLinkedList();
        circleSimpleLinkedList.list();
        circleSimpleLinkedList.addBoy(10);
        circleSimpleLinkedList.list();


    }
}

// 创建环形单向链表
class CircleSimpleLinkedList {

    // 创建first节点，当前无编号
    private Boy first = null;

    // 添加节点， 构建成环形链表
    // nums 节点个数
    public void addBoy(int nums) {

        if (nums < 1) {
            System.out.println("nums值不对");
            return;
        }

        Boy temp = null; // 辅助变量帮助创建环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号创建节点
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                boy.setNext(first);
                temp = first;
            } else {
                temp.setNext(boy);
                boy.setNext(first);
                temp = boy;
            }
        }
    }

    // 遍历当前环形链表
    public void list() {

        if (first == null) {
            System.out.println("== null");
            return;
        }

        Boy boy = first; // first 不能动， 使用辅助指针
        while (true) {
            System.out.printf("boy no: %d\n", boy.getNo());
            if (boy.getNext() == first) {
                break;
            }
            boy = boy.getNext();
        }
    }
}

class Boy {
    private int no; // 编号
    private Boy next; // next

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
