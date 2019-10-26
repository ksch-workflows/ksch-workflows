package ksch.assertions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WicketId {

    @NonNull
    private final String id;

    public String toString() {
        return id;
    }
}
