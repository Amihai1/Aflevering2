package Observable;

import EKG.DataListener;
import Listener.TempListener;

public interface TempObservable extends Runnable {
    void register(TempListener listener);
}
