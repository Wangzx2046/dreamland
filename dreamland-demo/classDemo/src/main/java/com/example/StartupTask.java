@Component
public class StartupTask {

    @PostConstruct
    public void init() {
        // 在这里编写启动后要执行的代码
        System.out.println("Spring Boot 启动后要执行的代码");
    }
}