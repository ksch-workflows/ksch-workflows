package ksch.util;

import lombok.SneakyThrows;
import org.junit.Assert;

import java.lang.reflect.Method;

public class CustomAssertions {

    /**
     * Checks that different implementations of a model type are equal.
     */
    public static void assertAllPropertiesEqual(Class model, Object a, Object b) {

        if (!model.isInstance(a) || !model.isInstance(b)) {
            throw new IllegalArgumentException("Objects to be compared need to be instances of the model class.");
        }

        Method[] methods = model.getDeclaredMethods();

        for (Method m : methods) {
            Object valueA = invokeMethod(m, a);
            Object valueB = invokeMethod(m, b);

            Assert.assertEquals(String.format("Object of type '%s' differ for method '%s()'.",
                    model.getName(), m.getName()), valueA, valueB);
        }
    }

    @SneakyThrows
    private static Object invokeMethod(Method method, Object object) {
        return method.invoke(object);
    }
}
