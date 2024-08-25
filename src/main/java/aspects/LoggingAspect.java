package aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Aspect
public class LoggingAspect {

    @Pointcut("execution(* jakarta.servlet.http.HttpServlet+.do*(..))")
    public void servletMethods() {}

    @Around("servletMethods() && args(request, response, ..)")
    public Object logAndAudit(ProceedingJoinPoint joinPoint, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Logging and auditing logic here
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        String user = (String) request.getSession(false).getAttribute("currentUser");

        System.out.println("User: " + user + " is invoking method: " + methodName);

        Object result = joinPoint.proceed();  // Proceed with the actual method

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Method: " + methodName + " executed in: " + duration + "ms");

        auditUserAction(user, methodName, duration);

        return result;
    }

    private void auditUserAction(String user, String action, long duration) {
        // Implement your auditing logic here
        System.out.println("Auditing user: " + user + " action: " + action + " duration: " + duration + "ms");
    }
}
