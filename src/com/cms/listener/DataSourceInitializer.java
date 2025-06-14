package com.cms.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

@WebListener
public class DataSourceInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(jakarta.servlet.ServletContextEvent sce) {
        // Initialize your data source here
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/cms_db");
        ds.setUsername("root");
        ds.setPassword("Ijse@1234");
        ds.setInitialSize(50);
        ds.setMaxTotal(100);

        ServletContext context = sce.getServletContext();
        context.setAttribute("dataSource", ds);

    }

    @Override
    public void contextDestroyed(jakarta.servlet.ServletContextEvent sce) {
        // Clean up your data source here
        try {
            ServletContext context = sce.getServletContext();
            BasicDataSource ds = (BasicDataSource) context.getAttribute("dataSource");
            ds.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
