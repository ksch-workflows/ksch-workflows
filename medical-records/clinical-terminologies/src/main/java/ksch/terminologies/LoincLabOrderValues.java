package ksch.terminologies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class LoincLabOrderValues {

    private static Map<String, LoincLabOrderValue> values;

    static  {
        values = new HashMap<>();

        List<String> lineInCsvFile = readLines("LoincUniversalLabOrdersValueSet.csv");

        for (int i = 1; i < lineInCsvFile.size(); i++) {
            String[] parts = lineInCsvFile.get(i).replace("\"", "").split(",");
            values.put(parts[0], new LoincLabOrderValue(parts[0], parts[1], parts[2]));
        }
    }

    public static List<LoincLabOrderValue> getAllValues() {
        return new ArrayList<>(values.values());
    }

    public static LoincLabOrderValue get(String loincNumber) {
        LoincLabOrderValue result = values.get(loincNumber);
        if (result == null) {
            throw new IllegalArgumentException(loincNumber);
        }
        return result;
    }

    private static List<String> readLines(String locator) {
        try {
            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(locator);
            if (resourceAsStream == null) {
                throw new IllegalArgumentException("Could not find resource: " + locator);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
            List<String> result = reader.lines().collect(toList());
            reader.close();
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Failed to process resource: " + locator, e);
        }
    }
}
