/** @author Amihai */

package Observable;

import Listener.SpO2Listener;
import Listener.TempListener;

public interface SpO2Observable extends Runnable {
    void register(SpO2Listener listener);
}
