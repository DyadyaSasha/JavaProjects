package clioptions;

import org.apache.commons.cli.*;

import java.util.Calendar;

public class BooleanOption {
    public static void main(String[] args) throws ParseException{

        Options options = new Options();
        options.addOption("t", false, "display time");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        Calendar date = Calendar.getInstance();
        int day = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH);
        int year = date.get(Calendar.YEAR);
        int hour = date.get(Calendar.HOUR);
        int min = date.get(Calendar.MINUTE);
        int sec = date.get(Calendar.SECOND);

        /**
         * если укажем в аргументах -t напечатается текущее время
         */
        System.out.print(day + "/" + month + "/" + year);
        if(cmd.hasOption("t")){
            System.out.println(" " + hour + ":" + min + ":" + sec);
        }
    }
}
