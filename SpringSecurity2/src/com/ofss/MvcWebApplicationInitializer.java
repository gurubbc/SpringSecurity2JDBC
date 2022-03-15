package com.ofss;  
/* Replacing web.xml */  
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;  
public class MvcWebApplicationInitializer extends  
        AbstractAnnotationConfigDispatcherServletInitializer 
        {  
    @Override  
    protected Class<?>[] getRootConfigClasses() {
    	System.out.println("inside getRootConfigClasses");
    	return new Class[] { WebSecurityConfig.class,AppConfig.class,DataSourceProvider.class };
    	// WebSecurityConfig - for security related information
    	// AppConfig is form view related information (prefix, suffix)
    	// DataSourceProvide is for returning DataSource with connections
    }  
    @Override  
    protected Class<?>[] getServletConfigClasses() {  
        System.out.println("Inside getServletConfigclasses method");  
        return null; // I am using DispatcherServlet  
    }  
    @Override  
    protected String[] getServletMappings() {
    	System.out.println("inside getServletMappings method");
    	return new String[] { "/" }; 
    }  
}  