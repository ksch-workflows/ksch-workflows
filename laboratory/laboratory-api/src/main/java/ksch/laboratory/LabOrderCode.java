package ksch.laboratory;

import lombok.RequiredArgsConstructor;

/**
 * https://loinc.org/usage/orders/
 */
@RequiredArgsConstructor
public class LabOrderCode {

    private final String loincNum;

    public String toString() {
        return loincNum;
    }
}
