package cliexamples;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.io.PrintWriter;

public class CLITester {
    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("p", "print", false, "Send print request to printer.")
                .addOption("g", "gui", false, "Show GUI Application")
                .addOption("n", true, "No. of copies to print");

        /**
         * класс, позволяющий отображать справочную информацию в надлежащем виде
         */
        HelpFormatter formatter = new HelpFormatter();
        /**
         * печатаем справочную информацию в консоль
         */
        formatter.printHelp("CLITester", options);
        System.out.println();
        /**
         * печатаем "пример" использования команды
         */
        final PrintWriter writer = new PrintWriter(System.out);
        formatter.printUsage(writer, 80, "CLITester", options);
        writer.flush();
    }
}
