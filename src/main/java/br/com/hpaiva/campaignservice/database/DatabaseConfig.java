package br.com.hpaiva.campaignservice.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private final String INSERT_SCRIPT = "classpath:db/populate.sql";

    @Autowired
    private DataSource datasource;

    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:conf/appContext.xml");

    @PostConstruct
    public void populateDatabase() throws Exception {
        Resource resource = ctx.getResource(INSERT_SCRIPT);
        ScriptUtils.executeSqlScript(datasource.getConnection(), resource);
    }

}
