package mirraim.tregulov_spring.aop.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointcuts {
    @Pointcut("execution(* mirraim.tregulov_spring.aop.Library.get*())")
    public void allGetMethods() {}

    @Pointcut("execution(* mirraim.tregulov_spring.aop.Library.add*(..))")
    public void allAddMethods() {}
}
