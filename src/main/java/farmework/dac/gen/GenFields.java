package farmework.dac.gen;

import com.google.common.collect.Maps;
import farmework.dac.factory.DataSourceFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class GenFields {
    private GenFields() {

    }

    public static void genFields(String db, String table) {
        String sql = "select COLUMN_NAME from `information_schema`.`COLUMNS` where `TABLE_SCHEMA`=:TABLE_SCHEMA and `TABLE_NAME`=:TABLE_NAME;";
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(DataSourceFactory.of().getDataSource());
        Map<String, String> params = Maps.newHashMap();
        params.put("TABLE_SCHEMA", db);
        params.put("TABLE_NAME", table);
        List<String> fields = template.queryForList(sql, params, String.class);

        // public interface AccountFields {
        //    String ID = "ID";
        //    String ACCOUNT_ID = "ACCOUNT_ID";
        //    String ACCOUNT_NAME = "ACCOUNT_NAME";
        //}

        String body = fields.stream()
                .map(field -> String.format("\tString %s = \"%s\";", field.toUpperCase(), field))
                .collect(Collectors.joining("\n"));

        // TODO
        // tb_account ==> TbAccount
        // tb_account_details ==> TbAccountDetails
        // account ==> Account

        String ifaceFormat = "public interface %sFields {\n\n" +
                "%s" +
                "\n}";

        String code = String.format(ifaceFormat, table, body);

        System.out.println(code);

        // 写文件。。。。
    }

    public static void main(String[] args) {
        genFields("demo", "tb_account");  // Account
    }
}
