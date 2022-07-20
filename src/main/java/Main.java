import exceptions.InvalidParameterException;
import me.lemire.integercompression.differential.IntegratedIntCompressor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.StringsRepository;

import java.io.IOException;


public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    /**
     * Checks if correct number of paramters passed by user. If correct, call doWork() method.
     * @param args
     * @throws IOException
     */
    public static void main(String[]args) throws IOException {

        if(args.length==3) {
            LOGGER.info(StringsRepository.VALID_NUM_OF_PARAMS);
            doWork(args);
        }else{
            throw new InvalidParameterException(StringsRepository.INVALID_NUM_OF_PARAMS);
        }
    }

    /**
     * Checks for valid first parameter/flag, which can be -c for compress or -d for decompress.
     * If the parameter is valid then will attempt to read CSV file containing integer array and
     * then compress or decompress depending on param inputted. The result will be written to a file.
     * @param args
     * @throws IOException
     */
    private static void doWork(String[] args){

        CsvManager csvManager = new CsvManager(args[1]);

        IntegratedIntCompressor iic = new IntegratedIntCompressor();

        if (args[0].matches("-c")) {

            LOGGER.info(StringsRepository.COMPRESS_FLAG_PRESENT);

            int[] uncompressedArray = csvManager.parseToIntArray(csvManager.parseCsv());
            int[] compressedArray = iic.compress(uncompressedArray);

            csvManager.writeCsv(compressedArray, args[2]);

        } else if (args[0].matches("-d")) {

            LOGGER.info(StringsRepository.DECOMPRESS_FLAG_PRESENT);

            int[] compressedArray = csvManager.parseToIntArray(csvManager.parseCsv());
            int[] uncompressedArray = iic.uncompress(compressedArray);

            csvManager.writeCsv(uncompressedArray, args[2]);
        } else {
            throw new InvalidParameterException(StringsRepository.INVALID_PARAM_EXCEPTION);
        }
    }
}
