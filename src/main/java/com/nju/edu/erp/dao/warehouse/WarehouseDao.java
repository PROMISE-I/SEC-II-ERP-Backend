package com.nju.edu.erp.dao.warehouse;

import com.nju.edu.erp.model.po.warehouse.WarehousePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WarehouseDao {
    void saveBatch(List<WarehousePO> warehousePOList);

    void deductQuantity(WarehousePO warehousePO);

    void addQuantity(WarehousePO warehousePO);

    List<WarehousePO> findAllNotZeroByPidSortedByBatchId(String pid);

    /**
     * 按照商品id获取现存商品（存量>0）并按价格排序
     * @param pid
     * @return
     */
    List<WarehousePO> findByPidOrderByPurchasePricePos(String pid);

    WarehousePO findOneByPidAndBatchId(String pid, Integer batchId);


    /**
     * 查看所有库存（库存盘点）
     * @return 所有库存
     */
    List<WarehousePO> findAll();
}
