package farmework.dac.service;

import farmework.dac.factory.DataSourceFactory;
import farmework.model.FieldEntity;
import farmework.model.RowEntity;
import farmework.util.RequiredUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class JdbcService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcService.class);

    // hutool, guava, commons-lang3, spring-util
    public int modify(String sql, Map<String, Object> params) {
        RequiredUtils.requireNotNullOrEmpty(sql, "sql should not be null or empty.");
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(DataSourceFactory.of().getDataSource());
        int effectRows = template.update(sql, params);
        LOGGER.info("execute jdbc update for sql = {}, effect rows = {}", sql, effectRows);
        return effectRows;
    }

    public List<RowEntity> query(String sql, Map<String, Object> params) {
        RequiredUtils.requireNotNullOrEmpty(sql, "sql should not be null or empty.");
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(DataSourceFactory.of().getDataSource());
        List<Map<String, Object>> maps = template.queryForList(sql, params);
        return maps.stream()
                .map(data -> new RowEntity(data.entrySet().stream()
                        .map(entry -> new FieldEntity(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
