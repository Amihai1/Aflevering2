package Calculator;

import Connectors.SerialConnector;
import DTO.BPMDTO;
import DTO.EKGDTO;
import Listener.BPMListener;
import Observable.BPMObservable;

import java.util.LinkedList;
import java.util.List;

public class BPMCalculator implements BPMObservable {




    @Override
    public void register(BPMListener listener) {
    }

    @Override
    public void run() {

       /* while (true) {
            try {
                int[] sensorData = new int[400];
                for (int i = 0; i < 200; i++) {//her hentes 200 returen array og målingerne sorteres i hvert sit array
                    sensorData[i] = serialConnector.getData();
                    System.out.println(sensorData.i);
                }

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}