package com.fca.dbedit.config

//import org.springframework.boot.autoconfigure.EnableAutoConfiguration
//import org.springframework.boot.context.embedded.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
//import org.springframework.web.servlet.DispatcherServlet
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
//import org.springframework.stereotype.Component
//
//import javax.servlet.ServletRegistration

/**
 * http://stackoverflow.com/questions/31724994/spring-data-rest-and-cors
 * WORKS!
 */
//@Configuration
//@EnableAutoConfiguration
//@Component
//public class RestConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

@Configuration
public class RestConfiguration {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // you USUALLY want this
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


    //http://stackoverflow.com/questions/22869901/how-to-get-dispatcherserveletinitializer-functionality-in-spring-boot
    //With this option, spring starts looking for /WEB-INF/dispatcherServlet-servlet.xml
//    @Bean
//    public ServletRegistrationBean dispatcherServlet() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(
//                new DispatcherServlet());
//        registration.setAsyncSupported(true);
//        Map<String,String> configMap = new HashMap<String,String>()
//        configMap.put("dispatchOptionsRequest", "true")
//        registration.setInitParameters(configMap)
//        return registration;
//    }

}