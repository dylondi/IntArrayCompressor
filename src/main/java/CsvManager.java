import exceptions.InvalidCsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.StringsRepository;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages the reading and parsing of CSV file data to int arrays and vice versa
 */
public class CsvManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvManager.class);

    private static String filePath;

    /**
     * Constructor
     * @param filePath
     */
    public CsvManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses a CSV file using an InputStream and a BufferedReader.
     * @return A list of Strings, each containing a line from the CSV file.
     */
    public List<String> parseCsv() {

        List<String> listOfRows;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        if (classLoader == null) {
            classLoader = getClass().getClassLoader();
        }

        LOGGER.info(StringsRepository.ATTEMPTING_TO_CREATE_BUFFEREDREADER_TO_PARSE_CSV);

        try (InputStream resource = classLoader.getResourceAsStream(filePath)) {
            listOfRows = new BufferedReader(new InputStreamReader(resource,
                            StandardCharsets.UTF_8)).lines().collect(Collectors.toList());

        } catch(NullPointerException e){
            throw new InvalidCsvException(StringsRepository.INVALID_CSV_EMPTY_NONEXISTENT_EXCEPTION);
        } catch(Exception e){
            throw new InvalidCsvException(e + StringsRepository.INVALID_CSV_EXCEPTION);
        }
        return listOfRows;
    }


    /**
     * Parses a List<String> object to and int array</String>
     * @param listOfLines
     * @return int[] object containing all data from each line of List
     */
    public int[] parseToIntArray(List<String> listOfLines) {

        LOGGER.info(StringsRepository.PARSING_STRING_LIST_TO_INT_ARRAY);

        String temp = "";

        for(String line : listOfLines){
            temp+=line;
        }

        String [] sArray = temp.replaceAll("\\s", "").split(",");

        int[] array = new int[sArray.length];

        for(int i=0; i< sArray.length; i++){
            try{
                array[i] = Integer.parseInt(sArray[i]);
            }catch(NumberFormatException e){
                throw e;
            }
        }
        return array;
    }

    /**
     * Writes an int array to a file defined by filePath variable
     * @param array
     * @param filePath
     */
    public void writeCsv(int[] array, String filePath) {

        try(FileWriter outputFile = new FileWriter(filePath)){

            LOGGER.info(StringsRepository.CREATING_CSV_FILE);

            String arr = Arrays.toString(array);
            outputFile.write(arr.substring(1, arr.length()-1).replaceAll("\\s", ""));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
