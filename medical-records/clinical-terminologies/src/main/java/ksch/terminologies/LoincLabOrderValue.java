package ksch.terminologies;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoincLabOrderValue {

    private final String loincNum;

    private final String longCommonName;

    private final String orderObs;
}
