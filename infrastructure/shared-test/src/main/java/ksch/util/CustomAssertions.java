/*
 * Copyright 2019 KS-plus e.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
