package Connectors;

import jssc.SerialPort;
import jssc.SerialPortList;

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
            serialPort.setParams(9600, 8, 1, SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
        } catch (Exception exception) {
            exception.printStackTrace(); // Så kan vi se, hvad vi får af fejl
        }
    }
    public int[] getData() {//metoden oprettes
        String[] rawValues = new String[400];//StringArray'et rawValues oprettes og længen bestemmes
        int ir = 0;//initialisering af lokale variable
        int red = 0;
        while (ir == 0 || red == 0) {//løkke oprettes
            try {
                if (serialPort.getInputBufferBytesCount() >= 12) {//kontrolstruktur
                    result = serialPort.readString();//strengen aflæses og tildeles result
                    Thread.sleep(20);//forsinkelse bestemmes til 20ms
                    if (result != null && result.charAt(result.length() - 1) == '#') {//result kontroleres
                        result = result.substring(0, result.length() - 1);//her fjernes det sidste index(#)
                        rawValues = result.split(" ");//nu splittes strengen og gemmes i et array
                    }
                    if (rawValues != null && rawValues.length >= 2) {//kontrollere om rawValues har nok indexer til konvertering
                        try {
                            ir = Integer.parseInt(rawValues[0]);//0. index konverteres til Integer og tildeles ir
                        } catch (Exception e) {//hvis der er et problem tildeles ir værdien 0
                            ir = 0;
                        }
                        try {
                            red = Integer.parseInt(rawValues[1]);//1. index konverteres til Integer og tildeles red
                        } catch (Exception e) {//hvis der er et problem tildeles red værdien 0
                            red = 0;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int[] returnArray = new int[]{ir, red};//returnArray oprettes med ir som 0. index og red som 1. index
        return returnArray;//returnArray returneres
    }
}
