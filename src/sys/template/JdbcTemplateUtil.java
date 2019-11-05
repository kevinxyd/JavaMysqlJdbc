package sys.template;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JdbcTemplateUtil {

    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
        InputStream resourceAsStream = JdbcTemplateUtil.class.getResourceAsStream("/jdbctemplate.properties");
        try {
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
