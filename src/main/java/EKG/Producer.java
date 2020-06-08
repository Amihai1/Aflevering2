package EKG;

import java.util.LinkedList;

public class Producer {
    LinkedList<Integer> list = new LinkedList<>();
    int capacity = 20;
    SerialConnector serialConnector;
    // Function called by producer thread
    public void produce() throws InterruptedException {

        while (true) {
            synchronized (this) {
                // producer thread waits while list
                // is full
                while (list.size() == capacity)
                    wait();
                int[] value = serialConnector.getData();
                System.out.println("Producer produced-"
                        + value);

                // to insert the jobs in the list
                list.add(value[0]);
                list.add(value[1]);

                // notifies the consumer thread that
                // now it can start consuming
                notify();

                // makes the working of program easier
                // to  understand
                Thread.sleep(100);
            }
        }
    }
}
