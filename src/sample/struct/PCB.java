package sample.struct;

/**
 * Created by kangfumei on 2017/3/25.
 */

/**
 *
 * @name PCB的名字
 * @rate 优先级
 * @stae PCB的状态
 * @ntime PCB需要的时间
 * @rtime PCB已经运行的时间
 * @next PCB的下一个进程
 *
 */

public class PCB {
    private String name;
    private int rate;
    private int state;
    private int ntime;
    private int rtime;
    private PCB next;

    public PCB(String name,int rate,int ntime){
        this.name = name;
        this.rate = rate;
        this.state = 0;
        this.rtime = 0;
        this.ntime = ntime;
    }

    public void show(){
        System.out.println( String.format("%s %d %d %d",name,state,ntime,rtime));
    }
}
