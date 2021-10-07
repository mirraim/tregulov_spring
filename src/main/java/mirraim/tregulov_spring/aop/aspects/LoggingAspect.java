package mirraim.tregulov_spring.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class LoggingAspect {

    @Before("mirraim.tregulov_spring.aop.aspects.MyPointcuts.allGetMethods()")
    public void beforeGetLoggingAdvice() {
        System.out.println("beforeGetBookAdvice: логгирование попытки получить книгу/журнал");
    }

//    @Pointcut("execution(* mirraim.tregulov_spring.aop.Library.return*())")
//    private void allReturnMethodsLibrary() {}
//
//    @Pointcut("execution(* mirraim.tregulov_spring.aop.Library.*(..))")
//    private void allMethodsLibrary() {}
//
//    @Pointcut("execution(public void mirraim.tregulov_spring.aop.Library.returnMagazine())")
//    private void returnMagazineLibrary() {}
//
//    @Pointcut("allGetMethodsLibrary() || allReturnMethodsLibrary()")
//    private void allGetAndReturnMethodsLibrary() {}
//
//    @Pointcut("allMethodsLibrary() && !returnMagazineLibrary()")
//    private void allMethodsExceptReturnMagazine() {}
//    @Before("allReturnMethodsLibrary()") // pointcut
//    public void beforeReturnLoggingAdvice() {
//        System.out.println("beforeReturnBookAdvice: writing Log #2");
//    }
//
//    @Before("allGetAndReturnMethodsLibrary()") // pointcut
//    public void beforeGetAndReturnLoggingAdvice() {
//        System.out.println("beforeGetAndReturnLoggingAdvice: writing Log #3");
//    }
//
//    @Before("allMethodsExceptReturnMagazine()")
//    public void beforeAllMethodsExceptReturnMagazineAdvice() {
//        System.out.println("beforeAllMethodsExceptReturnMagazineAdvice: log #10");
//    }


}
