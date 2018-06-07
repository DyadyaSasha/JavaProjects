package clioptions;

import org.apache.commons.cli.*;

public class ArgumentOption {
    public static void main(String[] args) throws ParseException {

        Options options = new Options();
        /**
         * класс, который описывает один аргумент/опцию
         */
        Option logFile = Option.builder()
                /**
                 * имя аргумента
                 */
                .longOpt("logFile")
                /**
                 * имя, которое будет отображаться для значения аргумента
                 */
                .argName("file")
                /**
                 * указывает, что данный аргумент обязателен
                 */
                .hasArg()
                /**
                 * описание аргумента
                 */
                .desc("use given file for log")
                .build();

        options.addOption(logFile);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if(cmd.hasOption("logFile")){
            /**
             * получаем значение аргумента
             */
            System.out.println(cmd.getOptionValue("logFile"));
        }
    }
}
