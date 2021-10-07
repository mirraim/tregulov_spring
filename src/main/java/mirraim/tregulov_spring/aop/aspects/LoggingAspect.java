package mirraim.tregulov_spring.aop.aspects;

import mirraim.tregulov_spring.aop.Book;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
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

    @Before("mirraim.tregulov_spring.aop.aspects.MyPointcuts.allAddMethods()")
    public void beforeAddLoggingAdvice(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.out.println("MethodSignature " + signature);
        System.out.println("MethodSignature.getMethod " + signature.getMethod());
        System.out.println("MethodSignature.getReturnType " + signature.getReturnType());
        System.out.println("MethodSignature.getName " + signature.getName());

        if (signature.getName().equals("addBook")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object obj : arguments) {
                if (obj instanceof Book) {
                    Book book = (Book) obj;
                    StringBuilder bookInfo = new StringBuilder("Информация о книге: ");
                    bookInfo.append("название - ")
                            .append(book.getName())
                            .append(", автор - ")
                            .append(book.getAuthor())
                            .append(", год публикации - ")
                            .append(book.getYearOfPublication());
                    System.out.println(bookInfo);
                }
                if (obj instanceof String) {
                    System.out.println("Книгу в библиотеку добавляет " + obj);
                }
            }
        }

        System.out.println("beforeAddBookAdvice: логгирование попытки добавить книгу/журнал");
        System.out.println("+++++++++++++++++++++++++++++++++++++");
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
