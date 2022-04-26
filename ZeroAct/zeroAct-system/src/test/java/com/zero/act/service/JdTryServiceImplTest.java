package test.com.zero.act.service.impl;

import com.zero.act.service.IJdTryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * JdTryServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>4�� 18, 2022</pre>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JdTryServiceImplTest {
    @Autowired
    IJdTryService iJdTryService;

    /**
     * Method: tryApply()
     */
    @Test
    public void testTryApply() throws Exception {
        iJdTryService.tryApply();
    }


} 
