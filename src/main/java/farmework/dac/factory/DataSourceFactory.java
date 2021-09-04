package farmework.dac.factory;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Maps;
import farmework.profile.ProfileHolder;
import farmework.util.YmlUtils;

import javax.sql.DataSource;
import java.util.Map;

public final class DataSourceFactory {

    private final Map<String, DataSource> dataSourceMap;

    private DataSourceFactory() {
        this.dataSourceMap = Maps.newConcurrentMap();
    }

    private static final class ClassHolder {
        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }

    public static DataSourceFactory of() {
        return new DataSourceFactory();
    }

    public DataSource getDataSource() {
        String configFile = "config-" + ProfileHolder.of().getProfile() + ".yml";
        if (this.dataSourceMap.containsKey(configFile)) {
            return this.dataSourceMap.get(configFile);
        }

        Map<String, String> configMap = YmlUtils.readForMap(configFile);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(configMap.get("mysql-url"));
        dataSource.setUsername(configMap.get("mysql-username"));
        dataSource.setPassword(configMap.get("mysql-password"));
        dataSource.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());

        this.dataSourceMap.put(configFile, dataSource);

        return dataSource;
    }
}
