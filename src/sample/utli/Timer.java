package sample.utli;

/**
 * Created by kangfumei on 2017/3/25.
 */
public class Timer extends Thread{
    private long time;

    public Timer(){
        time = 0;
        setDaemon(true);
    }

    public long getTime(){
        return time;
    }

    @Override
    public void run(){
        while (true){
            time+=1;
            System.out.println(time);
            try {
                sleep(1000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
