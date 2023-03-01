@Slf4j
public class TimerDemo {

    public static void main(String[] args) {
        Timer timer = new Timer();
        
        log.info("提交延迟任务");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("执行延迟任务");
            }
        }, 5000);
    }

}