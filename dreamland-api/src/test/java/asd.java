import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class asd {
    public static void main(String[] args) {

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        long s = Duration.between(LocalDateTime.parse("2021-03-19 16:09:19", dtf2), LocalDateTime.parse("2021-03-19 16:10:39", dtf2)).toMillis() / 1000;

        System.out.println("s=> " + s);
        BigDecimal low=BigDecimal.valueOf(1l);
        BigDecimal x=BigDecimal.valueOf(0);
        System.out.println(low.compareTo(x));

        if (1 ==low.compareTo(x) || -1 == low.compareTo(x) ){
            System.out.println("1111111");
        }else {
            System.out.println("222222222");
        }

    }
}
