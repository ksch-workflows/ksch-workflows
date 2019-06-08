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

package ksch;

import ksch.util.CustomAssertions;
import org.junit.Test;

public class CustomAssertionsTest {

    @Test
    public void should_compare_objects() {

        ExampleInterface objA = getExampleObject();
        ExampleInterface objB = getExampleObject();

        CustomAssertions.assertAllPropertiesEqual(ExampleInterface.class, objA, objB);
    }

    @Test
    public void should_compare_object_with_null_value() {

        ExampleInterface objA = getExampleObjectWithNullValue();
        ExampleInterface objB = getExampleObjectWithNullValue();

        CustomAssertions.assertAllPropertiesEqual(ExampleInterface.class, objA, objB);
    }
    
    @Test(expected = AssertionError.class)
    public void should_identify_non_equal_object() {

        ExampleInterface objA = getExampleObject();
        ExampleInterface objB = getExampleObjectWithNullValue();

        CustomAssertions.assertAllPropertiesEqual(ExampleInterface.class, objA, objB);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_reject_objects_which_dont_belong_to_the_model_class() {

        Class modelClass = ExampleInterface.class;
        ExampleInterface objA = getExampleObject();
        AnotherExampleInterface objB = getAnotherExampleObject();

        CustomAssertions.assertAllPropertiesEqual(modelClass, objA, objB);
    }

    private ExampleInterface getExampleObject() {
        return new ExampleInterface() {
            @Override
            public int getNumber() {
                return 42;
            }

            @Override
            public String getString() {
                return "s";
            }

            @Override
            public boolean isSet() {
                return false;
            }
        };
    }

    private ExampleInterface getExampleObjectWithNullValue() {
        return new ExampleInterface() {
            @Override
            public int getNumber() {
                return 42;
            }

            @Override
            public String getString() {
                return null;
            }

            @Override
            public boolean isSet() {
                return false;
            }
        };
    }

    private AnotherExampleInterface getAnotherExampleObject() {
        return () -> null;
    }
}
