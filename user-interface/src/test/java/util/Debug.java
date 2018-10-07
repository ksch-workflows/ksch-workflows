package util;

import org.apache.wicket.util.tester.WicketTester;

public class Debug {

    public static void printLastResponse(WicketTester tester) {
        System.out.println(tester.getLastResponseAsString());
    }
}
