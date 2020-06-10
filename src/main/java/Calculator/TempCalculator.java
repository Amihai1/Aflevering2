package Calculator;

import DTO.TempDTO;
import Listener.TempListener;
import EKG.DataObservable;
import Observable.TempObservable;

import java.sql.Timestamp;

public class TempCalculator implements TempObservable {
    private TempListener dataListener;

    @Override
    public void register(TempListener listener) {
        this.dataListener = listener;
    }

    @Override
    public void run() {
        while (true) {
            TempDTO tempDTO = new TempDTO();
            tempDTO.setTime(new Timestamp(System.currentTimeMillis()));
            tempDTO.setTemp(Math.floor(Math.random()*2)+36);
            if (dataListener != null){
                dataListener.notify(tempDTO);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
