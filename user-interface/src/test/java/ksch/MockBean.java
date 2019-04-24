package ksch;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class MockBean<T> {

    @NonNull
    private final Class<T> classToBeMocked;

    public Class<T> getBeanClass() {
        return classToBeMocked;
    }

    public Supplier<T> getSupplier() {
        return () -> Mockito.mock(classToBeMocked);
    }

    public static <T> MockBean mockBean(Class<T> classToBeMocked) {
        return new MockBean<>(classToBeMocked);
    }
}
