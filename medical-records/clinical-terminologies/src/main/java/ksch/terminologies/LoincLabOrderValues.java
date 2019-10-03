package ksch.terminologies;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LoincLabOrderValues {

    private static Map<String, LoincLabOrderValue> values;

    static  {
        values = new HashMap<>();
        List<String> lineInCsvFile = readLines("/LoincUniversalLabOrdersValueSet.csv");

        for (int i = 1; i < lineInCsvFile.size(); i++) {
            String[] parts = lineInCsvFile.get(i).replace("\"", "").split(",");
            values.put(parts[0], new LoincLabOrderValue(parts[0], parts[1], parts[2]));
        }
    }

    public static List<LoincLabOrderValue> getAllValues() {
        return new ArrayList<>(values.values());
    }

    private static List<String> readLines(String locator) {
        try {
            return Files.readAllLines(
                    Paths.get(resourceUri(locator)), Charset.defaultCharset());
        } catch (IOException e) {
            throw new IllegalArgumentException(locator);
        }
    }

    private static URI resourceUri(String locator) {
        URL resource = LoincLabOrderValues.class.getResource(locator);
        try {
            return resource.toURI();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Not a valid URI: " + locator);
        }
    }

    public static LoincLabOrderValue get(String loincNum) {
        LoincLabOrderValue result = values.get(loincNum);
        if (result == null) {
            throw new IllegalArgumentException(loincNum);
        }
        return result;
    }
}
