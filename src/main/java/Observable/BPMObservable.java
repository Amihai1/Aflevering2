/** @author Amihai */

package Observable;

import Listener.BPMListener;


public interface BPMObservable extends Runnable {
    void register(BPMListener listener);
}
