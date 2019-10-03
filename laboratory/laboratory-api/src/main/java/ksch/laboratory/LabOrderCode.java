package ksch.laboratory;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * https://loinc.org/usage/orders/
 */
@RequiredArgsConstructor
public class LabOrderCode {

    private final String loincNum;

    public String toString() {
        return loincNum;
    }

    public static List<LabOrderCode> labOrderCodes(String... loincNum) {
        List<LabOrderCode> result = new ArrayList<>();
        for (String s : loincNum) {
            result.add(new LabOrderCode(s));
        }
        return result;
    }
}
