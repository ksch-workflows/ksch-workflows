package ksch.laboratory;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * https://loinc.org/usage/orders/
 */
@RequiredArgsConstructor
@EqualsAndHashCode
public class LabOrderCode {

    private final String loincNum;

    public String toString() {
        return loincNum;
    }

    // TODO Maybe it is not necessary to create a list of multiple of lab order codes
    public static List<LabOrderCode> labOrderCodes(String... loincNum) {
        List<LabOrderCode> result = new ArrayList<>();
        for (String s : loincNum) {
            result.add(new LabOrderCode(s));
        }
        return result;
    }
}
