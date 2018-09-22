package org.leanhis;

import org.junit.Test;

public class CustomAssertionsTest {

    private ExampleInterface objA;
    private ExampleInterface objB;

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
}
