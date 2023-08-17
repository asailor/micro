package com.gdsig.system.idempotent;

/**
 * @author : xs
 * @date : 2023-08-17 10:52
 **/
public interface IdempotentStorage {

    /**
     * 保存
     *
     * @param idempotentId idempotentId
     */
    void save(String idempotentId);

    /**
     * 删除
     *
     * @param idempotentId idempotentId
     * @return boolean
     */
    boolean delete(String idempotentId);
}
