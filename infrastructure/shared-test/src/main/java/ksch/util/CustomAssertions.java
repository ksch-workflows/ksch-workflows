package ksch.util;

import lombok.SneakyThrows;
import org.junit.Assert;

import java.lang.reflect.Method;

public class CustomAssertions {

    /**
     * Checks that different implementations of a model type are equal.
     */
    public static void assertAllPropertiesEqual(Class dataClass, Object a, Object b) {

        if (!dataClass.isInstance(a) || !dataClass.isInstance(b)) {
            throw new IllegalArgumentException("Objects to be compared need to be instances of the data class '"
                    + dataClass.getSimpleName() + "'.");
        }

        Method[] methods = dataClass.getMethods();

        for (Method m : methods) {
            Object valueA = invokeMethod(m, a);
            Object valueB = invokeMethod(m, b);

            Assert.assertEquals(String.format("Object of type '%s' differ for method '%s()'.",
                    dataClass.getName(), m.getName()), valueA, valueB);
        }
    }

    @SneakyThrows
    private static Object invokeMethod(Method method, Object object) {
        return method.invoke(object);
    }
}
