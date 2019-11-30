package ksch.laboratory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {
    NOT_REQUIRED("Not required"),
    PENDING("Pending"),
    DONE("Done"),
    CANCELLED("Cancelled");

    private final String text;
}
