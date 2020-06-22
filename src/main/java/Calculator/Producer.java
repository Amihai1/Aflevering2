package Calculator;

import Connectors.SerialConnector;
import DTO.EKGDTO;
import Listener.EKGListener;
import Observable.EKGObservable;

import java.util.LinkedList;
import java.util.List;

public class Producer implements EKGObservable {
    LinkedList<EKGDTO> list = new LinkedList<>();
    LinkedList<EKGDTO> listDatabase = new LinkedList<>();
    int capacity = 400;
    SerialConnector serialConnector = new SerialConnector(0);
    private EKGListener listener;

    // Function called by producer thread
    public void Produce() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // producer thread waits while list
                // is full
                while (list.size() == capacity)
                    wait();
                List<EKGDTO> value = serialConnector.getData();
                if(value!=null){
                    for(EKGDTO i: value){
                        list.add(i);
                        //listDatabase.add(i);
                    }
                }

                // notifies the consumer thread that
                // now it can start consuming
                notify();

                // makes the working of program easier
                // to  understand
            }
        }
    }
    public void Consumer() throws InterruptedException {
        while(true){
            LinkedList<EKGDTO> listConsumer;
            synchronized (this) {
                while (list.size() < 50)
                    wait();
                listConsumer = list;
                list = new LinkedList<EKGDTO>();
                // Wake up producer thread

                // and sleep

                if (listener != null) {
                    listener.notify(listConsumer);
                }
                notify();

            }
        }
    }
    @Override
    public void run() {
        Thread pro = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread con = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        pro.start();
        con.start();
    }
    @Override
    public void register(EKGListener listener) {
        this.listener = listener;
    }


}
