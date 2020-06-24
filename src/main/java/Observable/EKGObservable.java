/** @author Amihai */

package Observable;

import Listener.EKGListener;

public interface EKGObservable {
    void register(EKGListener listener);
}
