package data.structure.linkedlist;
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

    private HeroNode head = new HeroNode(0,"", "");

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



    // 获取单聊表有效节点的个数
    // 单链表有头节点 则去掉头节点
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

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
