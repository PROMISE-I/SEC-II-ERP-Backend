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

### 展示层的分解

展示层使用 Vue 框架实现。

***TODO***

### 业务逻辑层的分解

业务逻辑层的开发包图见体系结构文档。

#### 库存模块

##### 模块概述

该模块包括商品分类管理、商品管理

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

#### 财务模块

##### 模块概述

该模块承担的需求参见需求规格说明文档功能需求及相关非功能需求。

模块的职责和接口参加软甲体系结构文档。

##### 整体结构

***TODO：整体结构***

##### 接口规范

***TODO：接口规范***

##### 业务逻辑层的动态模型

查看经营历程表的系统顺序图。***TODO：修改顺序图***

![image-20220709105358064](NJU-ERP%E7%B3%BB%E7%BB%9F%E8%AF%A6%E7%BB%86%E8%AE%BE%E8%AE%A1%E6%96%87%E6%A1%A3v1/image-20220709105358064.png)

单据状态图

![单据状态图](NJU-ERP%E7%B3%BB%E7%BB%9F%E8%AF%A6%E7%BB%86%E8%AE%BE%E8%AE%A1%E6%96%87%E6%A1%A3v1/201250127-status-diagram.jpg)

##### 业务逻辑层的设计原理

#### 人力资源模块

##### 模块概述

##### 整体结构

***TODO：整体结构***

##### 接口规范

***TODO：接口规范***

##### 业务逻辑层的动态模型

##### 业务逻辑层的设计原理

#### 企业经营管理模块

##### 模块概述

##### 整体结构

***TODO：整体结构***

##### 接口规范

***TODO：接口规范***

##### 业务逻辑层的动态模型

##### 业务逻辑层的设计原理

### 数据层的分解

数据层的分解参见体系结构文档相关内容。

## 依赖视角

NJU-ERP 系统开发包图（https://processon.com/diagraming/627093086376891e1c2106c1）