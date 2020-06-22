package com.ss.example.v1;

/**
 * OrderService
 *
 * @author shisong
 * @date 2020/6/22
 */
public interface OrderService {

    /**
     * 查询列表
     * @return 列表
     */
    String queryList();

    /**
     * 根据ID查询信息
     * @param id id
     * @return 信息
     */
    String queryInfoById(String id);

}
