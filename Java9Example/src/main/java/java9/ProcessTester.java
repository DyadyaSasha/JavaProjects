package java9;


import java.io.IOException;
import java.time.ZoneId;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessTester {
    public static void main(String[] args) throws IOException {
        /**
         * данный класс служит для построения процессов ОС
         * в конструктор передаём программу и её аргументы для создания нового процесса
         */
        ProcessBuilder processBuilder = new ProcessBuilder("notepad.exe");
        String np = "Not Present";
        /**
         * начинаем выполнение процесса, указанного в {@link ProcessBuilder}
         */
        Process process = processBuilder.start();
        /**
         * класс для контроля над процессом
         * {@link java.lang.ProcessHandle.Info} - содержит информацию о процессе
         */
        ProcessHandle.Info info = process.info();
        System.out.printf("Process ID : %s%n", process.pid());
        System.out.printf("Command name : %s%n", info.command().orElse(np));
        System.out.printf("Command line : %s%n", info.commandLine().orElse(np));

        System.out.printf("Start time: %s%n", info.startInstant().map(instant -> instant.atZone(ZoneId.systemDefault()).toLocalDateTime().toString()).orElse(np));
        System.out.printf("Arguments : %s%n", info.arguments().map(strings -> Stream.of(strings).collect(Collectors.joining(" "))).orElse(np));

        System.out.printf("User : %s%n", info.user().orElse(np));
    }
}
