package solvd.laba.itcompany.persistence.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class ConfigurationMyBatis {
    public static SqlSessionFactory getSessionFactory(){
        SqlSessionFactory sessionFactory;
        try (InputStream is = Resources.getResourceAsStream("myBatis-config.xml")){
            sessionFactory =  new SqlSessionFactoryBuilder()
                    .build(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sessionFactory;
    }

}
