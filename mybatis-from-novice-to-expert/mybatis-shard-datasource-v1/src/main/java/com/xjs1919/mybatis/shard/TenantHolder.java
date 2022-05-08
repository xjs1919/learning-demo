package com.xjs1919.mybatis.shard;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author jiashuai.xujs
 * @date 2022/4/1 13:46
 */
public class TenantHolder {

    public static TransmittableThreadLocal<String> tenantIdHolder = new TransmittableThreadLocal<String>();

    public static void setTenantId(String tenantId){
        tenantIdHolder.set(tenantId);
        DynamicDataSource.setDataSource(DataSourceEnum.getByTenantId(tenantId));
    }

    public static String getTenantId(){
        return tenantIdHolder.get();
    }

    public static void removeTenantId(){
        tenantIdHolder.remove();
        DynamicDataSource.removeDataSource();
    }

}
