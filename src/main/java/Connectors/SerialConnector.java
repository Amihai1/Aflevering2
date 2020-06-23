package Connectors;

import DTO.BPMDTO;
import DTO.EKGDTO;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.sql.Timestamp;
import java.util.ArrayList;
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
        try {
            if (serialPort.getInputBufferBytesCount() >= 8) {//kontrolstruktur
                result = serialPort.readString();//strengen aflæses og tildeles result
                String[] rawValues;
                if (result != null && result.charAt(result.length() - 1) == ' ') {//result kontroleres
                    rawValues = result.split(" ");//nu splittes strengen og gemmes i et array
                    List<EKGDTO> values = new LinkedList<>();
                    for (int i = 0; i < rawValues.length; i++) {
                        if (!Objects.equals(rawValues[i], "")) {
                            EKGDTO ekgdto = new EKGDTO();
                            ekgdto.setEkg((Integer.parseInt(rawValues[i])));
                            ekgdto.setTime(new Timestamp(System.currentTimeMillis()));
                            values.add(ekgdto);
                        }
                    }
                    return values;
                }
            }

        } catch (NumberFormatException | SerialPortException e) {
            e.printStackTrace();
        }
        return null;//returnArray returneres
    }

}
