# ERP系统软件体系结构描述文档

## 分工

| 学号      | 姓名   | 工作内容                                   |
| --------- | ------ | ------------------------------------------ |
| 201250172 | 熊丘桓 | 完成逻辑视角和开发包图                     |
| 201250127 | 蔡之恒 |                                            |
| 201250185 | 王福森 | 完成接口视角的数据层的分解和信息视角的编写 |
| 201250181 | 孙立帆 |                                            |



## 1. 引言

### 1.1 编制目的



### 1.2 词汇表



### 1.3 参考资料



## 2. 产品概述



## 3. 逻辑视角

ERP 系统中，选择了分层体系结构风格，将系统分为三层（展示层、业务逻辑层、数据层）能够很好地示意整个高层抽象。展示层包含 GUI 页面的实现，业务逻辑层包含业务逻辑处理的实现，数据层负责数据的持久化和访问。分层体系结构的逻辑视角和逻辑设计方案如下图所示。

![参照体系结构风格的包图表达逻辑视角](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250172-参照体系结构风格的包图表达逻辑视角.png)

![软件体系结构逻辑设计方案](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250172-软件体系结构逻辑设计方案.png)

## 4. 组合视角

### 4.1 开发包图

ERP 系统最终开发包设计如下表所示。

| 开发包               | 依赖的其他包                                                 |
| -------------------- | ------------------------------------------------------------ |
| mainui               | inventoryui, salesui, financeui, hrui, clientui, approvalui, strategyui, vo |
| inventoryui          | inventoryblservice, 界面类库包, vo                           |
| inventoryblservice   |                                                              |
| inventorybl          | inventoryblservice, inventorydataservice, po, utilitybl, salesbl |
| inventorydataservice |                                                              |
| inventorydata        | inventorydataservice, databaseutility                        |
| salesui              | salesblservice, 界面类库包, vo                               |
| salesblservice       |                                                              |
| salesbl              | salesblservice, salesdataservice, po, utilitybl              |
| salesdataservice     |                                                              |
| salesdata            | salesdataservice, databaseutility                            |
| financeui            | financeblservice, 界面类库包, vo                             |
| financeblservice     |                                                              |
| financebl            | financeblservice, financedataservice, po, utilitybl, salesbl |
| financedataservice   |                                                              |
| financedata          | financedataservice, databaseutility                          |
| hrui                 | hrblservice, 界面类库包, vo                                  |
| hrblservice          |                                                              |
| hrbl                 | hrblservice, hrdataservice, po, utilitybl, financebl         |
| hrdataservice        |                                                              |
| hrdata               | hrdataservice, databaseutility                               |
| clientui             | clientblservice, 界面类库包, vo                              |
| clientblservice      |                                                              |
| clientbl             | clientblservice, clientdataservice, po, utilitybl, salesbl   |
| clientdataservice    |                                                              |
| clientdata           | clientdataservice, databaseutility                           |
| approvalui           | approvalblservice, 界面类库包, vo                            |
| approvalblservice    |                                                              |
| approvalbl           | approvalblservice, approvaldataservice, po, utilitybl, inventorybl, salesbl, financebl |
| approvaldataservice  |                                                              |
| approvaldata         | approvaldataservice, databaseutility                         |
| strategyui           | strategyblservice, 界面类库包, vo                            |
| strategyblservice    |                                                              |
| strategybl           | strategyblservice, strategydataservice, po, utilitybl        |
| strategydataservice  |                                                              |
| strategydata         | strategydataservice, databaseutility                         |
| vo                   |                                                              |
| po                   |                                                              |
| databaseutility      |                                                              |

​		ERP 系统开发包图如下图所示。

![ERP系统开发包图](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250172-ERP系统开发包图.png)

### 4.2 运行时进程图



### 4.3 物理部署



## 5. 接口视角

### 5.1 模块的职责



### 5.2 用户界面的分解

#### 5.2.1 用户界面层模块的职责



#### 5.2.2 用户界面层的接口规范



#### 5.2.3 用户界面模块设计原理



### 5.3 业务逻辑层的分解

#### 5.3.1 业务逻辑层模块的职责



#### 5.3.2 业务逻辑层模块的接口规范



### 5.4 数据层的分解

数据层主要给业务逻辑层提供数据访问服务，包括对于持久化数据的增、删、改、查。Inventory业务逻辑、Sales业务逻辑需要的服务有CategoryDao、ProductDao、UserDao、WarehouseDao、WarehouseInputSheetDao、WarehouseOutputSheetDao、CustomerDao、RestockReturnedGoodsSheetDao、RestockSheetDao、SalesReturnedGoodsSheetDao、SalesSheetDao接口提供。在ERP系统中采用数据库来进行持久化数据的保存，借助Mybatis框架和Mapper映射文件完成数据层的具体实现。数据层模块的描述具体如下图所示。

![数据层模块的描述](lab5/201250185-%E6%95%B0%E6%8D%AE%E5%B1%82%E6%A8%A1%E5%9D%97%E7%9A%84%E6%8F%8F%E8%BF%B0.png)



#### 5.4.1 数据层模块的职责

数据层模块的职责入下表所示。

|     模块     |                             职责                             |
| :----------: | :----------------------------------------------------------: |
|    \*Dao     | 持久化数据库的接口，提供集体载入、集体保存、增、删、改、查服务 |
| \*Mapper.xml | 基于Mybatis框架的相应持久化数据库接口的实现，提供集体载入、集体保存、增、删、改、查服务 |



#### 5.4.2 数据层模块的接口规范

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



## 6. 信息视角

### 6.1 数据持久化对象

系统中的PO类就是对应的相关的实体类，ERP系统设计如下PO。

- CategoryPO 类包含分类id、分类名、父分类ID、是否为叶节点、商品数量、下一个商品index属性。
- ProductPO 类包含商品id、商品名、分类ID、商品型号、商品数量、进价、零售价、最近进价、最近零售价属性。
- User 类包含用户ID、用户姓名、用户密码、用户身份属性。
- WarehouseInputSheetContentPO 类包含入库商品列表id、入库单编号、商品id、商品数量、单价、出厂日期、备注、来源单据类型、来源单据编号属性。
- WarehouseInputSheetPO 类包含入库单编号、批次、操作员、操作时间属性。
- WarehouseOutputSheetContentPO 类包含出库商品列表id、商品id、出库单编号、批次、商品数量、单价备注、来源单据类型、来源单据编号属性。
- WarehouseOutputSheetPO 类包含出库单编号、操作员、操作时间属性。
- WarehousePO 类包含库存id、商品编号、数量、进价、批次、出厂日期属性。
- CustomerPO 类包含编号、分类、级别、姓名、电话、地址、邮编、电子邮箱、应收额度、应收、应付、默认业务员属性。
- RestockSheetPO 类包含进货单编号、供应商、仓库、操作员、备注、总额合计、操作时间属性。
- RestockReturnedGoodsSheetPO 类包含退货单编号、供应商、仓库、操作员、备注、总额合计、操作时间、对应的进货单编号属性。
- SalesSheetPO 类包含单据编号、客户、业务员、操作员、仓库、折让前总额、折让、使用代金卷金额、折让后总额、备注、操作时间属性。
- SalesReturnedGoodsSheetPO 类包含单据编号、客户、业务员、操作员、仓库、折让前总额、折让、使用代金卷金额、折让后总额、备注、操作时间、对应的销售单编号属性。



### 6.2 数据库表

数据库中包含Category表、Product表、User表、WarehouseInputSheetContent表、WarehouseInputSheet表、WarehouseOutputSheetContent表、WarehouseOutputSheet表、Warehouse表、Customer表、RestockSheet表、RestockReturnedGoodsSheet表、SalesSheet表、SalesReturnedGoodsSheet表。

