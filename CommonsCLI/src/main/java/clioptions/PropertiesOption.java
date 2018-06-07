package clioptions;

import org.apache.commons.cli.*;

import java.util.Properties;

public class PropertiesOption {
    public static void main(String[] args) throws ParseException{

        Options options = new Options();
        Option propertyOption = Option.builder()
                .longOpt("D")
                .argName("property=value")
                .hasArgs()
                /**
                 * используем "=", чтобы разделить аргумент от его значения
                 */
                .valueSeparator()
                /**
                 * устанавливаем кол-во значений, которые может принимать аргумент
                 */
                .numberOfArgs(2)
                .desc("use value for given properties")
                .build();

        options.addOption(propertyOption);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if(cmd.hasOption("D")){
            Properties properties = cmd.getOptionProperties("D");
            System.out.println("Class: " + properties.getProperty("class"));
            System.out.println("Roll No: " + properties.getProperty("rollNo"));
            System.out.println("Name: " + properties.getProperty("name"));
        }

    }
}
