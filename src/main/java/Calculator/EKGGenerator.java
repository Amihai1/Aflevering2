/** @author Amihai */
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

    Thread pro = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                producer.Produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    Thread con = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                producer.Consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });


    @Override
    public void run() {


        pro.start();
        con.start();


    }
}




