# NJU-ERP 系统详细设计文档v1

[TOC]

## 引言

### 编制目的

本报告详细完成对 NJU-ERP 系统的详细设计，达到指导后续软件构造的目的，同时实现和测试人员及用户的沟通。

本报告面向开发人员、测试人员及最终用户而编写，是了解系统的导航。

### 词汇表

| 词汇名称     | 词汇含义                     | 备注 |
| ------------ | ---------------------------- | ---- |
| NJU-ERP 系统 | 蓝鲸软件科技企业资源计划系统 |      |
|              |                              |      |
|              |                              |      |

### 参考资料

1. IEEE1471-2000
2. NJU-ERP 系统用例文档v1
3. NJU-ERP 系统软件需求规格说明文档v1
4. NJU-ERP 系统体系结构文档v1

## 产品概述

参考《NJU-ERP系统用例文档》和《NJU-ERP系统软件需求规格说明文档》中对产品的概括描述。

## 体系结构设计概述

参考《NJU-ERP系统体系结构设计文档》中对体系结构设计的概述。

## 结构视角

根据体系结构的设计，我们将软件系统分为展示层、业务逻辑层和数据层。每一层之间为了增加灵活性，我们会添加接口。

### 业务逻辑层的分解

业务逻辑层的开发包图见体系结构文档。

#### 库存模块

##### 模块概述

该模块包括商品分类管理、商品管理。

##### 整体结构

![库存模块类图](NJU-ERP%E7%B3%BB%E7%BB%9F%E8%AF%A6%E7%BB%86%E8%AE%BE%E8%AE%A1%E6%96%87%E6%A1%A3v1/%E5%BA%93%E5%AD%98%E6%A8%A1%E5%9D%97%E7%B1%BB%E5%9B%BE.png)

##### 接口规范

提供的服务（供接口）


| 接口名称                                | 语法                                                         | 前置条件             | 后置条件                       |
| :-------------------------------------- | ------------------------------------------------------------ | -------------------- | ------------------------------ |
| CategoryServiceImpl.createCategory      | public CategoryVO createCategory(Integer parentId, String name) | 需要新增一个商品分类 | 增加新增的商品分类信息         |
| CategoryServiceImpl.queryAllCategory    | public List\<CategoryVO\> queryAllCategory()                 | 需要查询商品分类     | 返回所有的商品分类             |
| CategoryServiceImpl.updateCategory      | public CategoryVO updateCategory(Integer id, String name)    | 需要更新商品分类     | 更新商品分类信息               |
| CategoryServiceImpl.deleteCategory      | public void deleteCategory(Integer id)                       | 需要删除一个商品分类 | 删除指定 Id 的商品分类         |
| CategoryServiceImpl.getUIInfo           | public String[] getUIInfo(String[] args)                     | 需要获取 UI 界面内容 | 返回所需的 UI 内容             |
| ProductServiceImpl.createProduct        | public ProductInfoVO createProduct(CreateProductVO inputVO） | 需要新增一个商品     | 增加新增的商品信息             |
| ProductServiceImpl.updateProduct        | public ProductInfoVO updateProduct(ProductInfoVO productInfoVO) | 需要更新商品信息     | 更新指定的商品信息             |
| ProductServiceImpl.queryAllProduct      | public List\<ProductInfoVO\> queryAllProduct()               | 需要查询商品信息     | 返回所有商品信息               |
| ProductServiceImpl.deleteById           | public void deleteById(String id)                            | 需要删除商品信息     | 删除指定 Id 的商品信息         |
| ProductServiceImpl.getUIInfo            | public String[] getUIInfo(String[] args)                     | 需要获取 UI 界面内容 | 返回所需的 UI 内容             |
| WarehouseServiceImpl.productWarehousing | public void productWarehousing(WarehouseInputFormVO warehouseInputFormVO) | 需要入库商品         | 更新库存中的商品信息(增加库存) |
| WarehouseServiceImpl.getUIInfo          | public String[] getUIInfo(String[] args)                     | 需要获取 UI 界面内容 | 返回所需的 UI 内容             |

需要的服务(需接口)

| 服务名                                                       | 服务                                       |
| ------------------------------------------------------------ | ------------------------------------------ |
| CategoryDao.findByCategoryId(Integer categoryId)             | 根据 Id 得到 CategoryPO 对象               |
| CategoryDao.createCategory(CategoryPO categoryPO)            | 向数据库中插入 CategoryPO对象              |
| CategoryDao.updateById(CategoryPO categoryPO)                | 更新数据库中的 CategoryPO 对象             |
| CategoryDao.findAll()                                        | 查询所有 CategoryPO 对象                   |
| CategoryDao.deleteById(Integer id)                           | 删除数据库中指定 Id 的CategotyPO对象       |
| ProductDao.createProduct(ProductPO productPO)                | 向数据库中插入 ProductPO 对象              |
| ProductDao.updateById(ProductPO productPO)                   | 更新数据库中的 ProductPO 对象              |
| ProductDao.findById(String id)                               | 根据 Id 查找 ProductPO 对象                |
| ProductDao.findAll()                                         | 查询所有 ProductPO 对象                    |
| ProductDao.deleteById(String id)                             | 删除数据库中指定 Id 的 ProductDao 对象     |
| WarehouseDao.saveBatch(List\<WarehousePO\> warehousePOList)  | 向数据库中插入一批 WarehousePO 对象        |
| WarehouseDao.deductQuantity(WarehousePO warehousePO)         | 减少一批商品的库存数量                     |
| WarehouseDao.findAllNotZeroByPidSortedByBatchId(String pid)  | 按 pid 查询一批商品，按 batchId 排序       |
| WarehouseInputSheetDao.getLatest()                           | 查询最近一条入库单(WarehouseInputSheetPO)  |
| WarehouseInputSheetDao.save(WarehouseInputSheetPO toSave)    | 存入一条入库单记录(WarehouseInputSheetPO)  |
| WarehouseInputSheetDao.saveBatch(List\<WarehouseInputSheetContentPO> warehouseInputListPOSheetContent) | 把入库单上的具体内容存入数据库             |
| WarehouseOutputSheetDao.getLatest()                          | 查询最近一条出库单(WarehouseOutputSheetPO) |
| WarehouseOutputSheetDao.save(WarehouseOutputSheetPO toSave)  | 存入一条出库单记录(WarehouseOutputSheetPO) |
| WarehouseOutputSheetDao.saveBatch(List\<WarehouseOutputSheetContentPO> warehouseOutputListPOSheetContent) | 把出库单上的具体内容存入数据库             |

#### 进货销售模块

##### 模块概述

该模块包括进货、进货退货、销售、销售退货、客户管理等功能。主要参与者是销售经理和销售管理人员。

##### 整体结构

![库存模块类图](NJU-ERP%E7%B3%BB%E7%BB%9F%E8%AF%A6%E7%BB%86%E8%AE%BE%E8%AE%A1%E6%96%87%E6%A1%A3v1/%E5%BA%93%E5%AD%98%E6%A8%A1%E5%9D%97%E7%B1%BB%E5%9B%BE.png)

##### 接口规范

提供的服务（供接口）

| 接口名称                               | 语法                                                         | 前置条件           | 后置条件                                        |
| :------------------------------------- | ------------------------------------------------------------ | ------------------ | ----------------------------------------------- |
| CustomerServiceImpl.createCustomer     | public CustomerVO createCustomer(CustomerInfoVO customerInfoVO) | 新增一个客户       | 增加新增的客户信息                              |
| CustomerServiceImpl.updateCustomer     | public CustomerVO updateCustomer(CustomerInfoVO customerInfoVO) | 需要更新客户信息   | 更新客户信息                                    |
| CustomerServiceImpl.deleteCustomer     | public void deleteCustomer(CustomerInfoVO customerInfoVO)    | 需要删除一个客户   | 删除和关键字段信息匹配的客户的信息              |
| CustomerServiceImpl.getLargestInPeriod | public CustomerVO getLargestInPeriod(Date startDate, Date endDate) | 需要查询交易       | 返回 startDate - endDate 日期内交易额最大的客户 |
| SalesServiceImpl.deliverGoods          | public SalesSheetVO deliverGoods(SalesSheetInfoVO salesSheetInfoVO) | 需要出货           | 更新本次出货涉及的库存和客户信息                |
| SalesServiceImpl.returnGoods           | public SalesReturnedGoodsSheetVO returnGoods(SalesReturnedGoodsSheetInfoVO salesReturnedGoodsInfoVO) | (顾客)需要退货     | 更新本次退货涉及到的客户和库存信息              |
| StockServiceImpl.restock               | public RestockSheetVO  restock(RestockSheetInfoVO restockSheetInfoVO) | 需要进货           | 更新库存信息                                    |
| StockServiceImpl.returnStock           | public RestockReturnedGoodsSheetVO returnStock(RestockReturnedGoodsSheetInfoVO restockReturnedGoodsSheetInfoVO) | 需要退货(给供应商) | 更新库存信息                                    |

需要的服务（需接口）

| 服务名                                                       | 服务                                                      |
| ------------------------------------------------------------ | --------------------------------------------------------- |
| CustomerDao.createCustomer(CustomerPO customerPO)            | 在数据库中插入一个新的CustomerPO                          |
| CustomerDao.updateById(CustomerPO customerPO)                | 更新一个 CustomerPO                                       |
| CustomerDao.findById(String Id)                              | 返回指定 Id 的 CustomerPO                                 |
| CustomerDao.deleteById(String Id)                            | 删除指定 Id 的 CustomerPO                                 |
| RestockSheetDao.getLatest()                                  | 获得最近的上一个 restockSheetPO 用于计算编号              |
| RestockSheetDao.save(RestockSheetPO toSave)                  | 向数据库中插入一个 RestockSheetPO                         |
| RestockSheetDao.saveContent(List\<WarehouseInputSheetContentPO > warehouseInputSheetContentPO) | 向数据库中插入一些WarehouseInputSheetContentPO            |
| SalesSheetDao.getLatest()                                    | 获得最近的上一个 SalesSheetPO 用于计算编号                |
| SalesSheetDao.save(SalesSheetPO toSave)                      | 向数据库插入一个 SalesSheetPO                             |
| SalesSheetDao.findAllByOperatorByFromTimeByToTime(String operator, Date fromTime, Date toTime) | 按操作员和时间区间查找返回许多SalesSheetPO                |
| SalesReturnedGoodsSheetDao.getLatest()                       | 获得最近的上一个 SalesReturnedGoodsSheetPO 用于计算编号   |
| SalesReturnedGoodsSheetDao.save(SalesReturnedGoodsSheetPO toSave) | 向数据库中插入一个 SalesReturnedGoodsSheetPO              |
| SalesReturnedGoodsSheetDao.saveContent(List\<WarehouseInputSheetContentPO> warehouseInputSheetContentPO) | 向数据库中插入一些 WarehouseInputSheetContentPO           |
| SalesReturnedGoodsSheetDao.findAllByOperatorByFromTimeByToTime(String operator, Date fromTime, Date toTime) | 按操作员和时间区间查找返回许多 SalesReturnedGoodsSheetPO  |
| RestockReturnedGoodsSheetDao.getLatest()                     | 获得最近的上一个 RestockReturnedGoodsSheetPO 用于计算编号 |
| RestockReturnedGoodsSheetDao.save(RestockReturnedGoodsSheetPO toSave) | 向数据库插入一个 RestockReturnedGoodsSheetPO              |
| RestockReturnedGoodsSheetDao.saveContent(List\<WarehouseOutputSheetContentPO> warehouseOutputSheetContentPO) | 向数据库中插入一些 WarehouseOutputSheetContentPO          |

#### 财务模块

##### 模块概述

该模块承担的需求参见需求规格说明文档功能需求及相关非功能需求。

模块的职责和接口参加软件体系结构文档。

##### 整体结构

该模块

![财务模块类图](NJU-ERP%E7%B3%BB%E7%BB%9F%E8%AF%A6%E7%BB%86%E8%AE%BE%E8%AE%A1%E6%96%87%E6%A1%A3v1/%E8%B4%A2%E5%8A%A1%E6%A8%A1%E5%9D%97%E7%B1%BB%E5%9B%BE.png)

##### 业务逻辑层的动态模型

查看经营历程表的系统顺序图。***TODO：修改顺序图***

![image-20220709105358064](NJU-ERP%E7%B3%BB%E7%BB%9F%E8%AF%A6%E7%BB%86%E8%AE%BE%E8%AE%A1%E6%96%87%E6%A1%A3v1/image-20220709105358064.png)

单据状态图：

![单据状态图](NJU-ERP%E7%B3%BB%E7%BB%9F%E8%AF%A6%E7%BB%86%E8%AE%BE%E8%AE%A1%E6%96%87%E6%A1%A3v1/201250127-status-diagram.jpg)

#### 人力资源模块

##### 模块概述

该模块包括

##### 整体结构

![人力资源模块类图](NJU-ERP%E7%B3%BB%E7%BB%9F%E8%AF%A6%E7%BB%86%E8%AE%BE%E8%AE%A1%E6%96%87%E6%A1%A3v1/%E4%BA%BA%E5%8A%9B%E8%B5%84%E6%BA%90%E6%A8%A1%E5%9D%97%E7%B1%BB%E5%9B%BE.png)

##### 接口规范

***TODO：接口规范***

##### 业务逻辑层的动态模型

##### 业务逻辑层的设计原理

#### 企业经营管理模块

##### 模块概述

##### 整体结构

![经营管理模块类图](NJU-ERP%E7%B3%BB%E7%BB%9F%E8%AF%A6%E7%BB%86%E8%AE%BE%E8%AE%A1%E6%96%87%E6%A1%A3v1/%E7%BB%8F%E8%90%A5%E7%AE%A1%E7%90%86%E6%A8%A1%E5%9D%97%E7%B1%BB%E5%9B%BE.png)

##### 接口规范

***TODO：接口规范***

##### 业务逻辑层的动态模型

##### 业务逻辑层的设计原理

### 数据层的分解

数据层主要为业务逻辑层提供数据访问服务，包括对可持久化对象的增删改查。

数据层主要给业务逻辑层提供数据访问服务，包括对于持久化数据的增、删、改、查。

Inventory 业务逻辑、Sales 业务逻辑需要的服务有 CategoryDao、ProductDao、UserDao、WarehouseDao、WarehouseInputSheetDao、WarehouseOutputSheetDao、CustomerDao、RestockReturnedGoodsSheetDao、RestockSheetDao、SalesReturnedGoodsSheetDao、SalesSheetDao接口提供。

在ERP系统中采用数据库来进行持久化数据的保存，借助Mybatis框架和Mapper映射文件完成数据层的具体实现。数据层模块的描述具体如下图所示。

![数据层模块的描述](NJU-ERP%E7%B3%BB%E7%BB%9F%E8%AF%A6%E7%BB%86%E8%AE%BE%E8%AE%A1%E6%96%87%E6%A1%A3v1/201250185-%E6%95%B0%E6%8D%AE%E5%B1%82%E6%A8%A1%E5%9D%97%E7%9A%84%E6%8F%8F%E8%BF%B0.png)

##### 数据层模块的职责

数据层模块的职责如下表所示。

| 模块        | 职责                                                         |
| :---------- | :----------------------------------------------------------- |
| *Dao        | 持久化数据库的接口，提供集体载入、集体保存、增、删、改、查服务 |
| *Mapper.xml | 基于 Mybatis 框架的相应持久化数据库接口的实现，提供集体载入、集体保存、增、删、改、查服务 |

##### 数据层模块的接口规范

数据层模块的接口规范如下表所示。

| 接口名称                                                     | 语法                                                         | 前置条件                                          | 后置条件                                            |
| :----------------------------------------------------------- | ------------------------------------------------------------ | :------------------------------------------------ | :-------------------------------------------------- |
| **CategoryDao**                                              |                                                              |                                                   |                                                     |
| CategoryDao.createCategory                                   | int createCategory(CategoryPO categoryPO);                   | 同样分类ID的PO在数据库中不存在                    | 在数据库中增加一个PO                                |
| CategoryDao.findByCategoryId                                 | CategoryPO findByCategoryId(Integer categoryId);             | 无                                                | 按照ID进行查找返回相应的CategoryPO结果              |
| CategoryDao.findAll                                          | List\<CategoryPO> findAll();                                 | 无                                                | 查找返回所有的CategoryPO结果                        |
| CategoryDao.updateById                                       | int updateById(CategoryPO categoryPO);                       | 在数据库中存在同样ID的PO                          | 更新一个PO                                          |
| CategoryDao.deleteById                                       | int deleteById(Integer id);                                  | 在数据库中存在同样ID的PO                          | 删除一个PO                                          |
| **ProductDao**                                               |                                                              |                                                   |                                                     |
| ProductDao.createProduct                                     | int createProduct(ProductPO productPO);                      | 同样商品ID的PO在数据库中不存在                    | 在数据库中增加一个PO                                |
| ProductDao.updateById                                        | int updateById(ProductPO productPO);                         | 在数据库中存在同样ID的PO                          | 更新一个PO                                          |
| ProductDao.findById                                          | findById(String id);                                         | 无                                                | 按照ID进行查找返回相应的ProductPO                   |
| ProductDao.findAll                                           | List\<ProductPO> findAll();                                  | 无                                                | 查找返回全部的ProductPO                             |
| ProductDao.deleteById                                        | int deleteById(String id);                                   | 在数据库中存在同样ID的PO                          | 删除一个PO                                          |
| **WarehouseDao**                                             |                                                              |                                                   |                                                     |
| WarehouseDao.saveBatch                                       | void saveBatch(List\<WarehousePO> warehousePOList);          | 同样库存ID的PO在数据库中不存在                    | 在数据库中增加许多PO                                |
| WarehouseDao.deductQuantity                                  | void deductQuantity(WarehousePO warehousePO);                | 在数据库中存在同样库存ID的PO                      | 更新一个PO                                          |
| WarehouseDao. findAllNotZeroByPidSortedByBatchId             | List\<WarehousePO> findAllNotZeroByPidSortedByBatchId(String pid); | 在数据库中存在同样批次ID                          | 按照批次ID进行查找返回全部WarehousePO               |
| **WarehouseInputSheetDao**                                   |                                                              |                                                   |                                                     |
| WarehouseInputSheetDao.getLatest                             | WarehouseInputSheetPO getLatest();                           | 在数据库中存在至少一个WarehouseInputSheetPO       | 查找返回updateTime最大的WarehouseInputSheetPO       |
| WarehouseInputSheetDao.save                                  | int save(WarehouseInputSheetPO toSave);                      | 同样入库单ID的PO在数据库中不存在                  | 在数据库中增加一个PO记录                            |
| WarehouseInputSheetDao.saveBatch                             | void saveBatch(List\<WarehouseInputSheetContentPO> warehouseInputListPOSheetContent); | 同样入库商品列表ID在数据库中不存在                | 在数据库中增加许多PO                                |
| **WarehouseOutputSheetDao**                                  |                                                              |                                                   |                                                     |
| WarehouseOutputSheetDao.getLatest                            | WarehouseOutputSheetPO getLatest();                          | 在数据库中存在至少一个WarehouseOutputSheetPO      | 查找返回updateTime最大的WarehouseOutputSheetPO      |
| WarehouseOutputSheetDao.save                                 | void save(WarehouseOutputSheetPO toSave);                    | 同样出库单ID的PO在数据库中不存在                  | 在数据库中增加一个PO记录                            |
| WarehouseOutputSheetDao.saveBatch                            | void saveBatch(List\<WarehouseOutputSheetContentPO> warehouseOutputListPOSheetContent); | 同样出库商品列表ID在数据库中不存在                | 在数据库中增加许多PO                                |
| **CustomerDao**                                              |                                                              |                                                   |                                                     |
| CustomerDao.createCustomer                                   | int createCustomer(CustomerPO customerPO);                   | 同样ID的PO在数据库中不存在                        | 在数据库中增加一个PO                                |
| CustomerDao.updateById                                       | int updateById(CustomerPO customerPO);                       | 在数据库中存在同样ID的PO                          | 更新一个PO                                          |
| CustomerDao.findById                                         | CustomerPO findById(String id);                              | 无                                                | 按ID进行查找返回相应的CustomerPO结果                |
| CustomerDao.deleteById                                       | int deleteById(String id);                                   | 在数据库中存在相同ID的PO                          | 删除一个PO                                          |
| **RestockSheetDao**                                          |                                                              |                                                   |                                                     |
| RestockSheetDao.getLatest                                    | RestockSheetPO getLatest();                                  | 在数据库中存在至少一个RestockSheetPO              | 查找返回updateTime最大的RestockSheetPO              |
| RestockSheetDao.save                                         | int save(RestockSheetPO toSave);                             | 同样进货单ID的PO在数据库中不存在                  | 在数据库中增加一个PO                                |
| RestockSheetDao.saveContent                                  | void saveContent(List\<WarehouseInputSheetContentPO > warehouseInputSheetContentPO); | 同样入库商品列表ID在数据库中不存在                | 在数据库中增加许多PO                                |
| **RestockReturnedGoodsSheetDao**                             |                                                              |                                                   |                                                     |
| RestockReturnedGoodsSheetDao.getLatest                       | RestockReturnedGoodsSheetPO getLatest();                     | 在数据库中存在至少一个RestockReturnedGoodsSheetPO | 查找返回updateTime最大的RestockReturnedGoodsSheetPO |
| RestockReturnedGoodsSheetDao.save                            | int save(RestockReturnedGoodsSheetPO toSave);                | 同样进货退货单ID的PO在数据库中不存在              | 在数据库中增加一个PO                                |
| RestockReturnedGoodsSheetDao.saveContent                     | void saveContent(List\<WarehouseOutputSheetContentPO> warehouseOutputSheetContentPO); | 同样出库商品列表ID在数据库中不存在                | 在数据库中增加许多PO                                |
| **SalesSheetDao**                                            |                                                              |                                                   |                                                     |
| SalesSheetDao.getLatest                                      | SalesSheetPO getLatest();                                    | 在数据库中存在至少一个SalesSheetPO                | 查找返回updateTime最大的SalesSheetPO                |
| SalesSheetDao.save                                           | int save(SalesSheetPO toSave);                               | 同样销售单ID的PO在数据库中不存在                  | 在数据库中增加一个PO                                |
| SalesSheetDao.saveContent                                    | void saveContent(List\<WarehouseOutputSheetContentPO> warehouseOutputSheetContentPO); | 同样出库商品列表ID在数据库中不存在                | 在数据库中增加许多PO                                |
| SalesSheetDao.findAllByOperatorByFromTimeByToTime            | List\<SalesSheetPO> findAllByOperatorByFromTimeByToTime(String operator, Date fromTime, Date toTime); | 无                                                | 按操作员和时间区间查找返回许多PO                    |
| **SalesReturnedGoodsSheetDao**                               |                                                              |                                                   |                                                     |
| SalesReturnedGoodsSheetDao.getLatest                         | SalesReturnedGoodsSheetPO getLatest();                       | 在数据库中存在至少一个SalesReturnedGoodsSheetPO   | 查找返回updateTime最大的SalesReturnedGoodsSheetPO   |
| SalesReturnedGoodsSheetDao.save                              | int save(SalesReturnedGoodsSheetPO toSave);                  | 同样销售退货单ID的PO在数据库中不存在              | 在数据库中增加一个PO                                |
| SalesReturnedGoodsSheetDao.saveContent                       | void saveContent(List\<WarehouseInputSheetContentPO> warehouseInputSheetContentPO); | 同样入库商品列表ID在数据库中不存在                | 在数据库中增加许多PO                                |
| SalesReturnedGoodsSheetDao.findAllByOperatorByFromTimeByToTime | List\<SalesReturnedGoodsSheetPO> findAllByOperatorByFromTimeByToTime(String operator, Date fromTime, Date toTime); | 无                                                | 按操作员和时间区间查找返回许多PO                    |

## 依赖视角

NJU-ERP 系统开发包图（https://processon.com/diagraming/627093086376891e1c2106c1）

![ERP系统开发包图](NJU-ERP%E7%B3%BB%E7%BB%9F%E8%AF%A6%E7%BB%86%E8%AE%BE%E8%AE%A1%E6%96%87%E6%A1%A3v1/ERP%E7%B3%BB%E7%BB%9F%E5%BC%80%E5%8F%91%E5%8C%85%E5%9B%BE.png)