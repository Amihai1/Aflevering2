package Calculator;

import DTO.SpO2DTO;
import DTO.TempDTO;
import Listener.SpO2Listener;
import Listener.TempListener;
import Observable.SpO2Observable;

import java.sql.Timestamp;

public class SpO2Calculator implements SpO2Observable {
    private SpO2Listener spo2Listener;
    @Override
    public void register(SpO2Listener listener) {
        this.spo2Listener = listener;
    }

    @Override
    public void run() {
        while (true) {
            SpO2DTO spo2DTO = new SpO2DTO();
            spo2DTO.setTime(new Timestamp(System.currentTimeMillis()));
            spo2DTO.setSpo2(String.valueOf(Math.floor(Math.random() * 2) + 98));
            if (spo2Listener != null){
                spo2Listener.notify(spo2DTO);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
