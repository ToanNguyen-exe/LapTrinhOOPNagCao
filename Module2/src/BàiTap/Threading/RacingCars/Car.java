package Threading.RacingCars;

public class Car implements Runnable{
    private String name;
    public Car(String name){
        this.name=name;
    }
    @Override
    public void run(){
        int runDistance=0;
        long startTime=System.currentTimeMillis();

    }
}
