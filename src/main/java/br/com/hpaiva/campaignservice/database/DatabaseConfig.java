package br.com.hpaiva.campaignservice.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Slf4j
@Configuration
public class DatabaseConfig {

    private final String INSERT_SCRIPT = "classpath:db/initial_script.sql";

    @Autowired
    private DataSource datasource;

    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:conf/appContext.xml");

    @PostConstruct
    public void populateDatabase() throws Exception {
        Resource resource = ctx.getResource(INSERT_SCRIPT);
        log.info("Executando o arquivo initial_script.sql no campaigndb...");
        ScriptUtils.executeSqlScript(datasource.getConnection(), resource);
        log.info("Execução do script database finalizado.");
    }

}
