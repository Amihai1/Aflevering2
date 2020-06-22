package Observable;

import Listener.EKGListener;

public interface EKGObservable extends Runnable {
    void register(EKGListener listener);
}
