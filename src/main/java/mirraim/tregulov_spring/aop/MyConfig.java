package mirraim.tregulov_spring.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("mirraim.tregulov_spring.aop")
@EnableAspectJAutoProxy
public class MyConfig {
}
