package Calculator;

import Connectors.SerialConnector;
import DTO.EKGDTO;
import Listener.EKGListener;
import Observable.EKGObservable;

import java.util.LinkedList;
import java.util.List;

public class EKGGenerator implements EKGObservable {
    LinkedList<EKGDTO> list = new LinkedList<>();
    LinkedList<EKGDTO> listDatabase = new LinkedList<>();
    int capacity = 400;
    SerialConnector serialConnector = new SerialConnector(0);
    // Function called by producer thread
    public void Produce() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // producer thread waits while list
                // is full
                while (list.size() == capacity && listDatabase.size() == capacity)
                    wait();
                List<EKGDTO> value = serialConnector.getData();
                if(value!=null){
                    for(EKGDTO i: value){
                        list.add(i);
                        listDatabase.add(i);
                        System.out.println("Producer produced-" + i);
                    }
                }
                // notifies the consumer thread that
                // now it can start consuming
                notify();

                // makes the working of program easier
                // to  understand
                Thread.sleep(10);
            }
        }
    }
    public void Consumer() throws InterruptedException {
        while(true){

            synchronized (this){
                while (list.size() == 0)
                    wait();

                // to retrive the ifrst job in the list
                LinkedList<Integer> removeObjekt = new LinkedList<>();
                list.removeAll(removeObjekt);

                System.out.println("Consumer consumed-");

                // Wake up producer thread
                notify();

                // and sleep
                Thread.sleep(100);
            }
        }
    }
    private EKGListener ekgListener;
    @Override
    public void register(EKGListener listener) {
        this.ekgListener = listener;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Produce();
               Consumer();
               if(ekgListener!=null){
                   ekgListener.notify();
               }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

