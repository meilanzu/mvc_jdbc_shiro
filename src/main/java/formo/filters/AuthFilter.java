package formo.filters;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class AuthFilter implements Filter {

    Factory<SecurityManager> factory;
    SecurityManager securityManager;

    public void init(FilterConfig config) throws ServletException {
        System.out.println("FILTER -> INIT ");
        String param = config.getInitParameter("auth-provider");
        System.out.println("Param: " + param);
        factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
    }


    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        System.out.println("FILTER -> DO FILTER ");

        HttpServletRequest request = (HttpServletRequest) req;
        String ipAddress = request.getRemoteAddr();
        System.out.println("IP " + ipAddress + ", Time " + new Date().toString());

        Subject currentUser = SecurityUtils.getSubject();
        System.out.println(securityManager);
        System.out.println("current user " + currentUser);
        if (currentUser.getPrincipal() != null) {
            System.out.println("NOT NULL >> " + currentUser.getPrincipal().toString());
        }
        chain.doFilter(req, res);
    }


    public void destroy() {
        System.out.println("FILTER -> DESTROY ");
    }

}