package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {
    private static CsvReader csvReader = null;

    private CsvReader(){}

    public static CsvReader getCsvReader(){
        if(csvReader == null){
            csvReader = new CsvReader();
        }
        return csvReader;
    }

    public ArrayList<String[]> readCsv(String csvPath, String separator, int numMoovers) throws IOException {
        BufferedReader bufferedReader = null;
        String line;
        ArrayList<String[]> arrayList = new ArrayList<String[]>();

        try {
            bufferedReader = new BufferedReader(new FileReader(csvPath));
            line = bufferedReader.readLine();
            while (line != null) {
                if(line.contains("M" + Integer.toString(numMoovers+1))){
                    break;
                }
                String[] data = line.split(separator);
                arrayList.add(data);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        return arrayList;
    }
}
