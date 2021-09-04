package farmework.profile;

import farmework.annotation.*;
import farmework.dac.factory.DataSourceFactory;

import javax.sql.DataSource;

@EnvProfile("abc")
public class App {

    @AutoTest
    @CheckPoint("xxx")
    @CaseTitle("xxxx")
    @CaseDesc(desc = "wwwww", owner = "qa")
    @CaseTag(key = "xxx", val = "xxx")
    @EnvProfile("abc") // 临时加，切换环境。
    public void testNormal() {
        System.out.println("App.testNormal");
    }

    @AutoTest
    @CheckPoint("xxx")
    @CaseTitle("xxxx")
    @CaseDesc(desc = "wwwww", owner = "qa")
    @CaseTag(key = "xxx", val = "xxx")
    public void testNormal1() {
        DataSource dataSource = DataSourceFactory.of().getDataSource();
        System.out.println("dataSource = " + dataSource);
    }
}
