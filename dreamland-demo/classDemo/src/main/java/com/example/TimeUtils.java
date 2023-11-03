import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    // 获取当前日期
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    // 获取当前时间
    public static LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    // 格式化日期
    public static String formatDate(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    // 格式化时间
    public static String formatTime(LocalDateTime time, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return time.format(formatter);
    }

}
