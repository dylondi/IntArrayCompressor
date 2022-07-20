package util;

public class StringsRepository {
    public static final String INVALID_CSV_EMPTY_NONEXISTENT_EXCEPTION = "NullPointerException: CSV file is empty or doesn't exist.";
    public static final String INVALID_CSV_EXCEPTION = "Could not load CSV file.";
    public static final String INVALID_PARAM_EXCEPTION = "Invalid flag. -c/-d must be used to decide compress or decompress.";
    public static final String ATTEMPTING_TO_CREATE_BUFFEREDREADER_TO_PARSE_CSV = "Attempting to create BufferedReader to read CSV file and parse each line into a list of Strings.";
    public static final String PARSING_STRING_LIST_TO_INT_ARRAY = "Parsing List of String to int array.";
    public static final String CREATING_CSV_FILE = "Creating CSV file with int array.";
    public static final String INVALID_NUM_OF_PARAMS = "Invalid number of parameters used. Three parameters required. -c/-d for compress/decompress, file to read, file to write.";
    public static final String VALID_NUM_OF_PARAMS = "Three arguments found in main method";
    public static final String COMPRESS_FLAG_PRESENT = "Compress flag inputted.";
    public static final String DECOMPRESS_FLAG_PRESENT = "Decompress flag inputted.";

}
