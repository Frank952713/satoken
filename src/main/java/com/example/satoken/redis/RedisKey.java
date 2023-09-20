package com.example.satoken.redis;

/**
 * Redis中相关的Key
 */
public class RedisKey {

    /**
     * 分布式锁
     */
    private static final String IMPORT_WORK_FORM = "workForm:importWorkForm:%s";

    /**
     * 获取导入工单的分布式锁
     */
    public static String getImportWorkFormKey(Long tenantId) {
        return String.format(IMPORT_WORK_FORM, tenantId);
    }
}
