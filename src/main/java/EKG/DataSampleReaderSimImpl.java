package EKG;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class DataSampleReaderSimImpl implements DataSampleReader{
    @Override
    public List<Double> loadData() {
        try {
            System.out.println(Paths.get("").toAbsolutePath());
            List<String> strings = Files.readAllLines(Paths.get("src/main/java/EKG/EKG100Hz"));
            System.out.println("Read data!");
            return strings.stream().map(s-> Double.parseDouble(s)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(PatientDTO patientDTO) {

    }


}