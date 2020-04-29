/*package EKG;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class EKGSimulator implements DataObservable,Runnable {
    private DataListener listener;
    private final List<Double> data;

    public EKGSimulator(){
        DataSampleReader sampleReader = new DataSampleReaderSimImpl();
        this.data = sampleReader.loadData();
    }

    public void run(){
        for (int i = 0; i < data.size(); i++) {
            try{
                Thread.sleep(9);
                if(listener!=null){
                    LocalDateTime now = LocalDateTime.now();
                    listener.notify(
                            new PatientDTO(data.get(i), new Timestamp(System.currentTimeMillis())));
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }


    @Override
    public void register(DataListener listener) {
    this.listener = listener;
    }
}
*/