package data.structure.linkedlist;

/**
 * author: Andy
 * 2020/12/15
 * Describe:
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.list();
        System.out.println("\n添加数据");
        doubleLinkedList.add(new HeroNode2(1, "111", "111-1"));
        doubleLinkedList.add(new HeroNode2(2, "222", "222-2"));
        doubleLinkedList.add(new HeroNode2(4, "444", "444-4"));
        doubleLinkedList.add(new HeroNode2(5, "555", "555-5"));
        doubleLinkedList.list();

        System.out.println("\n插入数据");
        doubleLinkedList.addByOrder(new HeroNode2(0, "000", "000-0"));
        doubleLinkedList.addByOrder(new HeroNode2(6, "666", "666-6"));
        doubleLinkedList.addByOrder(new HeroNode2(3, "333", "333-3"));
        doubleLinkedList.list();

        System.out.println("\n修改数据");
        doubleLinkedList.update(new HeroNode2(3, "331", "331-1"));
        doubleLinkedList.update(new HeroNode2(2, "221", "221-1"));
        doubleLinkedList.list();

        System.out.println("\n删除数据");
        doubleLinkedList.del(2);
        doubleLinkedList.list();

    }
}

class DoubleLinkedList {

    // 头结点
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    // 添加节点到末尾
    public void add(HeroNode2 node) {
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = node;
        node.pre = temp;
    }


    // 添加节点到末尾
    public void addByOrder(HeroNode2 newNode) {
        HeroNode2 temp = head;
        while (temp.next != null) {

            if (temp.next.no > newNode.no ) {   // 找到节点
                newNode.next = temp.next;
                temp.next.pre = newNode;
                temp.next = newNode;
                newNode.pre = temp;
                break;
            }

            temp = temp.next;
        }

        /**
         * 未找到， 插入到末尾
         */
        temp.next = newNode;
        newNode.pre = temp;
    }

    // 修改节点
    public void update(HeroNode2 node) {
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (temp.no == node.no) {
                temp.name = node.name;
                temp.nickname = node.nickname;
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (!flag) {
            System.out.println("未找到~");
        }

    }

    // 删除节点
    // 直接找到需要删除的节点， 找到后自我删除即可
    public void del(int no) {

        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (temp.no == no) {
                temp.pre.next = temp.next;
                // 需要判断是否是最后一个节点
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (!flag) {
            System.out.println("未找到....");
        }
    }

    // 打印双向链表
    public void list() {
        if (head.next == null) {
            System.out.println("双向链表为空");
            return;
        }

        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public HeroNode2(HeroNode2 heroNode) {
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
