package Connectors;

import DTO.EKGDTO;
import jssc.SerialPort;
import jssc.SerialPortList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SerialConnector {
    private SerialPort serialPort = null;
    private String result;

    public SerialConnector(int portnummer) {
        //lav en sensor til at

        String[] portnames = SerialPortList.getPortNames();
        // opret et String Array til at læse portnavne.

        try {
            for (int i = 0; i < portnames.length; i++) {
                // lige så mange gange vi har porte, krer vi en gang igennem
                String port = portnames[i];
                serialPort = new SerialPort(port);
                //lav et nyt portobjekt ud fra en plads.
                System.out.println("har lavet en port fra første port" + port);
            }
            serialPort = new SerialPort(portnames[portnummer]);
            serialPort.openPort();
            serialPort.setRTS(true);
            serialPort.setDTR(true);
            serialPort.setParams(115200, 8, 1, SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
        } catch (Exception exception) {
            exception.printStackTrace(); // Så kan vi se, hvad vi får af fejl
        }
    }

    public List<EKGDTO> getData() {//metoden oprettes
        // String[] rawValues = new String[400];//StringArray'et rawValues oprettes og længen bestemmes
        try {
            if (serialPort.getInputBufferBytesCount() >= 12) {//kontrolstruktur
                result = serialPort.readString();//strengen aflæses og tildeles result
                String[] rawValues;
                if (result != null && result.charAt(result.length() - 1) == ' ') {//result kontroleres
                    result = result.substring(0, result.length() - 1);//her fjernes det sidste index(#)
                    rawValues = result.split(" ");//nu splittes strengen og gemmes i et array
                    List<EKGDTO> values = new LinkedList<>();
                    for (int i = 0; i < rawValues.length; i++) {
                            EKGDTO ekgdto = new EKGDTO();
                            ekgdto.setEkg(Double.parseDouble(rawValues[i]));
                            values.add(ekgdto);
                    }
                    return values;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;//returnArray returneres
    }
}
