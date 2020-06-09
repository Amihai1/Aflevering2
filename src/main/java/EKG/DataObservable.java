package EKG;

public interface DataObservable extends Runnable{
    //som registrere på interfacet DataListener
    //Her registrere vi, at vi er interreseret i data
    void register(DataListener listener);
}
