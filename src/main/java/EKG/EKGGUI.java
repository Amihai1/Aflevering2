package EKG;

import javafx.event.ActionEvent;

public class EKGGUI implements DataListener {
    public String text;
    public javafx.scene.text.Text text2;
    public javafx.scene.text.Text text3;
    public javafx.scene.shape.Polyline Linje;
    private double possition = 0.0;

    public void buttonPressed(ActionEvent actionEvent) throws Exception {
        DataController.generator.register(this);

    }
    public void LoadData(ActionEvent actionEvent)throws Exception{

    }
    @Override
    public void notify(PatientDTO data) {


        if (possition < 700) {
            Linje.getPoints().addAll(possition, -data.getSample());
            possition += 1;
        } else {
            Linje.getPoints().clear();
            possition = 0.0;
        }
       // text += "Temperatur: " + data.getTemp();
        /*if(possition == 50.0) {
            try {
                //text += "Temperatur: " + PatientDTO.
                //text2.setText("BPM: " + String.valueOf();
                //text3.setText("SpO2: "+ String.valueOf(PatientDataGenerator.DummySpO2()));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }*/

        //System.out.println("opdating line");
    }
}

