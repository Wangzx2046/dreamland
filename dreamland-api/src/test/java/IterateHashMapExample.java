import java.util.HashMap;
import java.util.Map;

/**
 * 在 Java 中遍历 HashMap 的5种最佳方法
 * @author Ramesh Fadatare
 *
 */
public class IterateHashMapExample {
    public static void main(String[] args) {
        Map < Integer, String > coursesMap = new HashMap < Integer, String > ();
        coursesMap.put(1, "C");
        coursesMap.put(2, "C++");
        coursesMap.put(3, "Java");
        coursesMap.put(4, "Spring Framework");
        coursesMap.put(5, "Hibernate ORM framework");

        // 4. 使用 Lambda 表达式遍历 HashMap
        coursesMap.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });
    }
}