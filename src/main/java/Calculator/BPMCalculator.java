/** @author Amihai */
package Calculator;

import Connectors.SerialConnector;
import DTO.BPMDTO;
import DTO.EKGDTO;
import Listener.BPMListener;
import Observable.BPMObservable;

import java.util.LinkedList;
import java.util.List;

public class BPMCalculator implements BPMObservable{
    private BPMListener bpmListener;

    @Override
    public void register(BPMListener listener) {
        this.bpmListener = listener;
    }

    @Override
    public void run() {
        while (true) {
            EKGDTO ekgdto = new EKGDTO();
            BPMDTO bpmdto = new BPMDTO();
            List<EKGDTO> ekgdtos = new LinkedList<>();
            List<Integer> ekg = new LinkedList<>() ;
            for (EKGDTO i:ekgdtos){
                ekg.add(i.getEkg());
            }
            int beat = 0;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            for (int i = 0; i < ekg.size(); i++) {
                if (min > ekg.get(i)) {
                    min = ekg.get(i);
                }
                if (ekg.get(i) > max) {
                    max = ekg.get(i);
                }
            }
            double avg = 0.25 * min + 0.75 * max;
            boolean counted = false;
            for (int i = 0; i < ekg.size(); i++) {
                if (ekg.get(i) > avg) {
                    if (!counted) {
                        beat++;
                        counted = true;
                    } else {
                        counted = false;
                    }
                }
            }
            double bpm = beat * 60;
            bpmdto.setBpm(bpm);
            System.out.println(bpm);
            if(bpmListener !=null){
                bpmListener.notify(bpmdto);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}