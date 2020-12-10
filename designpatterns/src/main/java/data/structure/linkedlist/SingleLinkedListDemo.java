package data.structure.linkedlist;

import data.structure.queue.ArrayQueue;

/**
 * author: Andy
 * 2020/12/10
 * Describe:
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {

    }


    class SingleLinkedList {
        private HeroNode head = new HeroNode(0,"", "");

        // 添加到结尾
        // next指向新的节点
        public void add(HeroNode heroNode) {
            // head节点不能动， 辅助变量 temp
            HeroNode temp = head;
            while (true) {
                if (temp.next == null) {
                    break;
                }

                temp = temp.next;
            }

            temp.next = heroNode;
        }

        // 按照指定顺序添加节点
        public void addByOrder(HeroNode heroNode) {
            HeroNode temp = head;
            boolean flag = false;  // 标识是否存在

            while (true) {
                if (temp.next == null)  // 已经在链表最后
                    break;

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
            HeroNode temp = head;
            boolean flag = false;
            while (true) {
                if (temp.next == null) {
                    break;
                }

                if (temp.next.no == no) {
                    flag = true;
                }

                temp = temp.next;
            }

            // 找到
            if (flag) {
                temp.next = temp.next.next;
            }

        }


        // 根据节点的信息， 根据no编号修改， no编号不能修改
        public void update(HeroNode heroNode) {
            if (head.next == null) {
                System.out.println("链表为null");
                return;
            }

            HeroNode temp = heroNode.next;
            boolean flag = false;

            while (true) {
                if (temp == null) {
                    break;  //遍历完链表
                }


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
                System.out.println("链表为null");
                return;
            }

            HeroNode temp = head.next;
            while (true) {
                if (temp.next == null) {
                    break;
                }

                System.out.println(temp.toString());
                temp = temp.next;
            }


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
}
