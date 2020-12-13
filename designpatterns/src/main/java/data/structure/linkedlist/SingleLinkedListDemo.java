package data.structure.linkedlist;

import java.util.Stack;

/**
 * author: Andy
 * 2020/12/10
 * Describe:
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        singleLinkedList.add(new HeroNode(1, "aaa", "aaa-1"));
        singleLinkedList.add(new HeroNode(2, "bbb", "bbb-1"));
        singleLinkedList.add(new HeroNode(3, "ccc", "ccc-1"));
        singleLinkedList.add(new HeroNode(5, "ddd", "ddd-1"));
        singleLinkedList.add(new HeroNode(6, "eee", "eee-1"));
        singleLinkedList.add(new HeroNode(7, "fff", "fff-1"));

        log("构建列表：");
        singleLinkedList.list();

        log("删除 no = 5");
        singleLinkedList.del(5);
        singleLinkedList.list();

        log("添加4：");
        singleLinkedList.addByOrder(new HeroNode(4, "444", "444-1"));
        singleLinkedList.list();

        log("修改6：");
        singleLinkedList.update(new HeroNode(6, "666", "666-1"));
        singleLinkedList.list();


        log("获取倒数第1个：");
        HeroNode heroNode = singleLinkedList.findLastIndexNode(singleLinkedList.getHead(), 1);
        if (heroNode == null) {
            log("meiyou index");
        } else {
            log(heroNode.toString());
        }

        log("逆序打印：");
        singleLinkedList.reverseList();

        log("反转链表：");
        //singleLinkedList.reverse(singleLinkedList.getHead());
        log("    origin: ");
        singleLinkedList.list(singleLinkedList.getHead());
        log("    reverse: ");
        singleLinkedList.list(singleLinkedList.getReverseHead());

        log("    reverse2: ");
        singleLinkedList.reverse2(singleLinkedList.getHead());
        singleLinkedList.list();
    }

    private static void log(String str) {
        System.out.println(str);
    }
}

class SingleLinkedList {

    //获取头节点
    public HeroNode getHead() {
        return head;
    }

    //获取reverse头节点
    public HeroNode getReverseHead() {
        return reverseHead;
    }

    private HeroNode head = new HeroNode(0,"", "");
    private HeroNode reverseHead = new HeroNode(0,"", "");

    // 添加到结尾
    // next指向新的节点
    public void add(HeroNode heroNode) {
        // head节点不能动， 辅助变量 temp
        HeroNode temp = head;

        // while 循环找到最后一个位置
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = heroNode;
    }

    // 按照指定顺序添加节点
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;  // 标识是否存在

        while (temp.next != null) {  // 找指定位置
            if (temp.next.no > heroNode.no) {  // 位置找到， 添加
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }

        // 根据flag 判断是否存在， 存在则不添加
        if (!flag) {
            // 插入到链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
        } else {
            System.out.println("已经存在");
        }

    }

    // 删除指定节点
    public void del(int no) {
        HeroNode temp = head;   // 指向头节点 判断头节点下一个不为空
        boolean flag = false;
        while (temp.next != null) {

            if (temp.next.no == no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }

        // 找到
        if (flag) {
            temp.next = temp.next.next;
        } else{
            System.out.println("del 未找到");
        }
    }

    // 根据节点的信息， 根据no编号修改， no编号不能修改
    public void update(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("链表为null");
            return;
        }

        HeroNode temp = head.next;
        boolean flag = false;

        while (temp != null) {

            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            System.out.println("没有找到");
        }

    }


    // 展示链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表length = " + 0);
            return;
        }

        System.out.println("链表length = " + getLength(getHead()));
        HeroNode temp = head.next;
        while (temp != null) {   //指向头节点下一个的话 判断temp不为null
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    public void list(HeroNode headNode) {
        if (headNode.next == null) {
            System.out.println("链表length = " + 0);
            return;
        }

        System.out.println("链表length = " + getLength(headNode));
        HeroNode temp = headNode.next;
        while (temp != null) {   //指向头节点下一个的话 判断temp不为null
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }


    // 逆序打印
    // 利用栈的方式 进行逆序打印
    public void reverseList() {
        Stack<HeroNode> heroNodeStack = new Stack<>();
        HeroNode curr = head.next;
        while (curr != null) {
            heroNodeStack.push(curr);
            curr = curr.next;  // 后移
        }

        while (heroNodeStack.size() > 0) {
            System.out.println(heroNodeStack.pop());
        }
    }


    /**
     * 查找单链表中倒数第k个节点 (新浪)
     * 思路 1， head节点， index： 倒数index节点，
     *      2， 正向  length - index  //正向序列
     *      3, 找到返回该节点， 未找到null
     */
    public HeroNode findLastIndexNode(HeroNode heroNode, int index) {
        if (heroNode.next == null)
            return null;
        int length = getLength(heroNode);
        if (index <= 0 || index > length) {
            return null;
        }

        HeroNode curr = heroNode.next;
        for (int i = 0; i < length - index; i++) {
            curr = curr.next;
        }

        return curr;
    }


    // 获取单聊表有效节点的个数
    // 单链表有头节点 则去掉头节点
    /**
     * 获取链表的长度
     */
    public int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }

        int length = 0;
        // 辅助变量  没有统计头节点
        HeroNode cur = head.next;

        while (cur != null) {
            length ++;
            cur = cur.next;
        }


        return length;
    }


    /**
     * 1, 定义反向链表头节点 reverseHead
     * 2， 遍历原来链表，并取出放到新链表的表头
     * 3, reverseHead.next = 取出的节点
     */
    // 原链表不变  复制到新链表  数据反转
    public void reverse(HeroNode head) {
        if (head.next == null) {
            System.out.println("原链表为 null");
            return;
        }

        HeroNode temp = head.next;
        HeroNode temp2;
        while (temp != null) {
            temp2 = new HeroNode(temp);
            temp2.next = reverseHead.next;
            reverseHead.next = temp2;
            temp = temp.next;
        }

    }

    // 反转链表本身  head 最后指向 reverseHead
    public void reverse2(HeroNode head) {
        // 链表为null 或者 仅有一个元素
        if (head.next == null || head.next.next == null) {
            System.out.println("原链表为 null");
            return;
        }

        HeroNode temp;
        while (head.next != null) {
            temp = head.next;
            head.next = temp.next; // 原链表中去掉

            // 加入新链表
            temp.next = reverseHead.next;
            reverseHead.next = temp;
        }

        head.next = reverseHead.next;

    }
}

// 定义HeroNode, 每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public HeroNode(HeroNode heroNode) {
        this.no = heroNode.no;
        this.name = heroNode.name;
        this.nickname = heroNode.nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
