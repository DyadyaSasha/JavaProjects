package firstapp;

import org.apache.commons.cli.*;

/**
 * Данная библиотека предназначена для парсинга аргументов командной строки запускаемой программы
 *
 */
public class CliTester {

    public static void main(String[] args) throws ParseException {

        /**
         * класс, представляющий аргументы командной строки в программе
         */
        Options options = new Options();
        /**
         * добавляем опцию/аргумент командной строки
         * второй аргумент показывает, обязателен ли аргумент в командной строке
         */
        options.addOption("a", false, "add numbers");
        options.addOption("m", false, "multiply numbers");
        /**
         * Класс для парсинга командной строки
         */
        CommandLineParser parser = new DefaultParser();
        /**
         * парсим строку, указываем аргументы(options) и входной массив аргументов командной строки
         */
        CommandLine cmd = parser.parse(options, args);
        /**
         * идём по аргументам командной строки и вызываем методы, соответсвующие аргументам
         */
        if(cmd.hasOption("a")){
            System.out.println("Sum of the numbers: " + getSum(args));
        } else if(cmd.hasOption("m")){
            System.out.println("Multiplication of the numbers: " + getMultiplication(args));
        }

    }

    private static int getSum(String[] args){
        int sum = 0;
        for(int i = 1; i < args.length; i++){
            sum += Integer.parseInt(args[i]);
        }
        return sum;
    }

    private static int getMultiplication(String[] args){
        int multiplication = 1;
        for(int i = 1; i < args.length; i++){
            multiplication *= Integer.parseInt(args[i]);
        }
        return multiplication;
    }

}
