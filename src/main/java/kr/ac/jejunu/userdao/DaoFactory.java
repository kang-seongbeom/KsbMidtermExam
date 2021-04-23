package kr.ac.jejunu.userdao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {

    @Value("${db.classname}")
    String classname;
    @Value("${db.url}")
    String url;
    @Value("${db.name}")
    String name;
    @Value("${db.password}")
    String password;

    @Bean
    public UserDao userDao() {
        return new UserDao(jdbcTemplate());
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(getConnection());
    }

    @Bean
    public DataSource getConnection() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(classname));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setUrl(url);
        dataSource.setUsername(name);
        dataSource.setPassword(password);
        return dataSource;
    }
}
