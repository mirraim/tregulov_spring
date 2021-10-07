package mirraim.tregulov_spring.aop.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointcuts {
    @Pointcut("execution(* mirraim.tregulov_spring.aop.Library.get*())")
    public void allGetMethods() {}

    @Pointcut("execution(* add*(..))")
    public void allAddMethods() {}
}
