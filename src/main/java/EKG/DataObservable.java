package EKG;

public interface DataObservable extends Runnable{
    void register(DataListener listener);
}
