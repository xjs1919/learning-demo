package com.xjs1919.mybatis.shard;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author jiashuai.xujs
 * @date 2022/4/1 13:58
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final TransmittableThreadLocal<DataSourceEnum> dataSourceHolder = new TransmittableThreadLocal<DataSourceEnum>();

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceEnum dataSource = getDataSource();
        log.info("DynamicDataSource determineCurrentLookupKey:{}",dataSource);
        return dataSource;
    }

    public static DataSourceEnum getDataSource() {
        return dataSourceHolder.get();
    }

    public static void setDataSource(DataSourceEnum dataSource) {
        dataSourceHolder.set(dataSource);
    }

    public static void removeDataSource() {
        dataSourceHolder.remove();
    }
}