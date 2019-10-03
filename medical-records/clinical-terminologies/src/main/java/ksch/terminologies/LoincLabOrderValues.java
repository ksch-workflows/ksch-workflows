package ksch.terminologies;

import lombok.SneakyThrows;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LoincLabOrderValues {

    private static Map<String, LoincLabOrderValue> values;

    public static List<LoincLabOrderValue> getAllValues() {
        if (values == null) {
            init();
        }
        return new ArrayList<>(values.values());
    }

    @SneakyThrows
    private static void init() {

        URL resource = LoincLabOrderValues.class.getResource("/LoincUniversalLabOrdersValueSet.csv");
        List<String> lineInCsvFile = Files.readAllLines(
                Paths.get(resource.toURI()), Charset.defaultCharset());

        values = new HashMap<>();

        for (String s : lineInCsvFile) {
            values.put(UUID.randomUUID().toString(), new LoincLabOrderValue("", "", ""));
        }

    }
}
