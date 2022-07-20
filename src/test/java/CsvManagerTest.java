import exceptions.InvalidCsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class CsvManagerTest {
    String fileName;

    @org.junit.jupiter.api.Test
    void parseCsvNonExistentCsvFile() {
        fileName = "non-existent";
        CsvManager csvParser = new CsvManager(fileName);

        Assertions.assertThrows(InvalidCsvException.class, () -> csvParser.parseCsv());
    }

    @org.junit.jupiter.api.Test
    void parseToIntArrayInvalidCsvContainingNonDigits() {
        fileName = "integers-test-1";
        CsvManager csvParser = new CsvManager(fileName);

        Assertions.assertThrows(NumberFormatException.class, () -> csvParser.parseToIntArray(csvParser.parseCsv()));

    }

        @org.junit.jupiter.api.Test
        void writeCsv() {
            String fileName = "integers-test-2";
            CsvManager csvParser = new CsvManager(fileName);

            int [] array = csvParser.parseToIntArray(csvParser.parseCsv());

            csvParser.writeCsv(array, "src\\main\\resources\\integer-test-2-result");
            CsvManager csvParserTwo = new CsvManager("integer-test-2-result");
            int[] arrayTwo = csvParserTwo.parseToIntArray(csvParserTwo.parseCsv());
            assertArrayEquals(array, arrayTwo);
        }




}