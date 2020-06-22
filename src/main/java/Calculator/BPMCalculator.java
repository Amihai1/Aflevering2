package Calculator;

import Listener.BPMListener;
import Observable.BPMObservable;

public class BPMCalculator implements BPMObservable {
    private BPMListener bpmListener;
    @Override
    public void register(BPMListener listener) {
        this.bpmListener = listener;
    }

    @Override
    public void run() {

    }
}
