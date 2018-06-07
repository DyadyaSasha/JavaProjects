package utilityclasses;

import org.apache.commons.io.IOCase;

public class IOCaseTester {
    public static void main(String[] args) {

        String text = "Welcome to TutorialsPoint. Simply Easy Learning.";
        String text1 = "WELCOME TO TUTORIALSPOINT. SIMPLY EASY LEARNING.";
        /**
         * {@link IOCase} обрабатывает чувствительность к регистру в разных OS
         */
        System.out.println("Ends with Learning (case sensitive): " +
                IOCase.SENSITIVE.checkEndsWith(text1, "Learning."));

        System.out.println("Ends with Learning (case insensitive): " +
                IOCase.INSENSITIVE.checkEndsWith(text1, "Learning."));

        System.out.println("Equality Check  (case sensitive): " +
                IOCase.SENSITIVE.checkEquals(text, text1));

        System.out.println("Equality Check  (case insensitive): " +
                IOCase.INSENSITIVE.checkEquals(text, text1));

    }
}
