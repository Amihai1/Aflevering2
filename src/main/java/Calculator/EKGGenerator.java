package Calculator;

import Connectors.SerialConnector;
import DTO.EKGDTO;
import Listener.EKGListener;
import Observable.EKGObservable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class EKGGenerator implements EKGObservable, Runnable {
    private EKGListener ekgListener;
    Producer producer = new Producer();

    @Override
    public void register(EKGListener listener) {
        producer.register(listener);
    }




    @Override
    public void run() {




    }


}

