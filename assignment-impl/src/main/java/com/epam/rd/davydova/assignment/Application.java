package com.epam.rd.davydova.assignment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

@Slf4j
public class Application implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        // Создаём корневой контекст
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.scan("com.epam.rd.davydova.assignment");

        // Передаём управление жизненным циклом корневого контекста Сервлет контексту
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // Создаём контекст для dispatcher servlet'а
        AnnotationConfigWebApplicationContext dispatcherContext =
                new AnnotationConfigWebApplicationContext();
        dispatcherContext.scan("com.epam.rd.davydova.assignment");

        // Регистрируем dispatcher servlet
        ServletRegistration.Dynamic dispatcher =
                servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");

        log.info("Application is running");
    }
}