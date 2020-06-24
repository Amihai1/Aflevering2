/** @author Amihai */

package Observable;

import Listener.TempListener;

public interface TempObservable extends Runnable {
    void register(TempListener listener);
}
