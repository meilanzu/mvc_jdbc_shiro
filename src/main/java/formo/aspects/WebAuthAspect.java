package formo.aspects;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class WebAuthAspect {

    @Before("@target(org.springframework.stereotype.Controller) && @annotation(requiresRoles)")
    public void assertAuthorized(JoinPoint jp, RequiresRoles requiresRoles) {
        SecurityUtils.getSubject().checkRoles(Arrays.asList(requiresRoles.value()));
    }
}