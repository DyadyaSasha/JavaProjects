package newdateandtime;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class NewDateTimeTester {
    public static void main(String[] args) {
        /**
         * {@link LocalDateTime} используется для работы с датой и временем без учёта временных зон
         *
         * текущее время
         */
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Current DateTime: " + currentTime);

        /**
         * {@link LocalDate} используется для работы с датами без учёта временных зон
         */
        LocalDate date = currentTime.toLocalDate();
        System.out.println("Date: " + date);

        /**
         * {@link java.time.Month} для работы с месяцами
         */
        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();
        System.out.println("Month: " + month +"day: " + day +"seconds: " + seconds);

        LocalDateTime date1 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("Date1: " + date1);

        LocalDate date2 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("Date2: " + date2);

        /**
         * {@link LocalTime} для работы с временем без учета временных зон
         */
        LocalTime time = LocalTime.of(22, 15);
        System.out.println("Time: " + time);

        LocalTime time1 = LocalTime.parse("20:15:30");
        System.out.println("Time1: " + time1);

        /**
         * {@link ZonedDateTime} используется для работы с датами и временем с учётом временных зон
         */
        ZonedDateTime dateTime = ZonedDateTime.now();
        System.out.println("ZonedDateTime: " + dateTime);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("CurrentZone: " + currentZone);


        /**
         * {@link java.time.temporal.ChronoUnit} содержит значения соответствующие дню, месяцу, году
         */
        LocalDate today = LocalDate.now();
        System.out.println("Current date: " + today);

        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Next week: " + nextWeek);

        LocalDate nextMonth = today.plus(1, ChronoUnit.MONTHS);
        System.out.println("Next month: " + nextMonth);

        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("Next year: " + nextYear);

        LocalDate nextDecade = today.plus(1, ChronoUnit.DECADES);
        System.out.println("Date after ten year: " + nextDecade);

        /**
         * {@link Period} и {@link Duration} для работы с временными промежутками
         */
        LocalDate date3 = LocalDate.now();
        LocalDate date4 = date3.plus(1, ChronoUnit.MONTHS);

        Period period = Period.between(date4, date3);
        System.out.println("Period: " + period);

        LocalTime time2 = LocalTime.now();
        Duration  twoHours = Duration.ofHours(2);

        LocalTime time3 = time2.plus(twoHours);
        Duration duration = Duration.between(time2, time3);

        System.out.println("Duration: " + duration);


        /**
         * {@link java.time.temporal.TemporalAdjusters} используется для "математических" над датами
         */
        LocalDate nextTuesday = date3.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        System.out.println("Next Tuesday on : " + nextTuesday);

        LocalDate first = LocalDate.of(date3.getYear(), date3.getMonth(), 1);
        LocalDate secondSaturday = first.with(TemporalAdjusters.nextOrSame(
                DayOfWeek.SATURDAY)).with(TemporalAdjusters.next(DayOfWeek.SATURDAY)
        );
        System.out.println("Second Saturday on : " + secondSaturday);

        /**
         * В старых классах для работы с датами: {@link java.util.Date} и {@link java.util.Calendar}
         * есть меотод toInstant(), чтобы конвертировать в новый DateTime Api Java 8
         */

        Date currentDate = new Date();
        System.out.println("Current date: " + currentDate);

        /**
         * {@link Instant} - аналог {@link Date}
         */
        Instant now = currentDate.toInstant();
        ZoneId currentZon = ZoneId.systemDefault();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, currentZon);
        System.out.println("Local date: " + localDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now, currentZon);
        System.out.println("Zoned date: " + zonedDateTime);

        /**
         * также есть и другие классы, подробнее:
         * https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html
         */


    }
}
