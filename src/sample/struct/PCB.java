package sample.struct;

/**
 * Created by kangfumei on 2017/3/25.
 */

/**
 *
 * @name PCB的名字
 * @rate 优先级
 * @stae PCB的状态
 * @atime 到达时间
 * @ntime PCB需要的时间
 * @rtime PCB已经运行的时间
 * @next PCB的下一个进程
 *
 */

public class PCB implements Comparable<PCB>{
    private String name;
    private int rate;
    private int state;
    private int atime;
    private int ntime;
    private int rtime;
    private PCB next;

    public PCB(){
    }

    public PCB(String name,int rate,int ntime,int atime){
        this.name = name;
        this.rate = rate;
        this.atime = atime;
        this.state = 0;
        this.rtime = 0;
        this.ntime = ntime;
        this.next = null;
    }

    public void show(){
        PCB h = this;
        while (h!=null){
            System.out.println(String.format("%s %d %d %d\n",h.getName(),h.getRate(),h.getAtime(),h.getRtime()));
            h = h.getNext();
        }
    }

    public void sort(){
        PCB p1,p2,pre1,pre2;
        p1 = this;

        while (p1!=null){
            pre1=p1;
            p1=p1.getNext();
            if(pre1.getRate()<p1.getRate()){
                pre1.setNext(p1);
                p1=pre1;
            }

        }
    }

    public String getName() {
        return name;
    }

    public int getAtime() {
        return atime;
    }

    public int getRtime() {
        return rtime;
    }

    public int getRate() {
        return rate;
    }

    public int getNtime() {
        return ntime;
    }

    public PCB getNext() {
        return next;
    }

    public void setRtime() {
        this.rtime = this.rtime+1;
    }

    public void setNext(PCB next) {
        this.next = next;
    }

    public boolean runOver(){
        return this.rtime == this.ntime;
    }

    @Override
    public int compareTo(PCB o) {
        return this.rate>o.getRate()?this.rate:o.getRate();
    }

    public PCB merge_sort(PCB head) {
        if (head == null || head.next == null) {
            return head;
        }
        PCB middle = getMiddle(head); // get the middle of the list
        PCB sHalf = middle.next;
        middle.next = null; // split the list into two halfs

        return merge(merge_sort(head), merge_sort(sHalf)); // recurse on that
    }

    // Merge subroutine to merge two sorted lists
    public PCB merge(PCB a, PCB b) {
        PCB dummyHead, curr;
        dummyHead = new PCB();
        curr = dummyHead;
        while (a != null && b != null) {
            if (a.getRate() <= b.getRate()) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }
        curr.next = (a == null) ? b : a;
        return dummyHead.next;
    }

    // Finding the middle element of the list for splitting
    public PCB getMiddle(PCB head) {
        if (head == null) {
            return head;
        }
        PCB slow, fast;
        slow = fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
