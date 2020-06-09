package EKG;

import java.sql.Timestamp;

public class PatientDataGenerator implements DataObservable {
    private DataListener DataSampler;


    @Override
    public void register(DataListener listener) {
        this.DataSampler = listener;
    }

    //Hernede har vi en endeløs lykke, som generere data.
    @Override
    public void run() {
        while (true) {
            PatientDTO patientDTO = new PatientDTO();
            patientDTO.setTime(new Timestamp(System.currentTimeMillis()));
            patientDTO.setBPM( Math.floor(Math.random() * 30) + 50);
            patientDTO.setSpO2(Math.floor(Math.random() * 2) + 98);
            patientDTO.setTemp( Math.floor((double) Math.random() * 2) + 36);
            //hvis der så er ny data, så meddeler vi at der er kommet ny data.
            if (DataSampler != null){
                DataSampler.notify(patientDTO);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
