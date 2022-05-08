package com.xjs1919.mybatis.shard;

/**
 * @author jiashuai.xujs
 * @date 2022/4/1 13:49
 */
public enum DataSourceEnum {

    SHARD0(0, "shard0"),
    SHARD1(1, "shard1");


    private int dbIndex;

    private String label;

    DataSourceEnum(int dbIndex, String label){
        this.dbIndex = dbIndex;
        this.label = label;
    }

    public Integer getValue() {
        return dbIndex;
    }

    public String getLabel() {
        return label;
    }

    public static DataSourceEnum getByTenantId(String tenantId){
        int hashCode = tenantId.hashCode();
        int dbIndex = Math.abs(hashCode%2);
        DataSourceEnum[] dataSources = DataSourceEnum.values();
        for(DataSourceEnum dataSource : dataSources){
            if(dbIndex == dataSource.getValue()){
                return dataSource;
            }
        }
        return null;
    }

}
