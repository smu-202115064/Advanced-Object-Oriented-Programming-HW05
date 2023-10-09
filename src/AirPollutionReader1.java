import java.util.Observable;
import java.util.Observer;

public class AirPollutionReader1 implements Observer, Runnable {
    private AirPollutionServer1 server;
    private int sleepDuration;
    private boolean stop = false;
    private int num;
    
    public AirPollutionReader1(AirPollutionServer1 server, int num, int duration) {
        this.server = server;
        this.sleepDuration = duration;
        this.num = num;
        server.addObserver(this);
    }

    @Override
    public void run() {
        while (!stop) {        
            try {
                Thread.sleep(sleepDuration); // sleepDuration밀리초 멈춤
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void stopThread() { stop = true; }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof AirPollutionServer1) {
            AirPollutionServer1 server = (AirPollutionServer1) o;
            System.out.printf("readerNumber = %d, pollution = %d\n", num, server.getPollution());
        }
    }
}