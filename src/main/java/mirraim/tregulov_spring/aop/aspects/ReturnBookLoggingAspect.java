package mirraim.tregulov_spring.aop.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ReturnBookLoggingAspect {

    /**
     * метод с аннотацией @Around перехватывает целевой метод
     * Если не выполнить целевой метод внутри Advice, то целевой метод не отработает
     * выполнить целевой метод и получить результат его работы можно с помощью proceedingJoinPoint.proceed,
     * который возвращает Object
     * @param proceedingJoinPoint может все то же, что обычный JoinPoint
     * @return результат можно изменить перед возвратом значения, но это плохая практика
     * @throws Throwable throws
     */
    @Around("execution(public String returnBook())")
    public Object aroundReturnBookAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("aroundReturnBookAdvice: логгирование попытки возврата книги в библиотеку");
        long begin = System.currentTimeMillis();
        Object targetMethodRsl = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("aroundReturnBookAdvice: логгирование возврата книги в библиотеку");
        System.out.println("aroundReturnBookAdvice: метод returnBook " +
                "выполнил работу за " + (end - begin) + " миллисекунд");
        return targetMethodRsl;
    }
}
