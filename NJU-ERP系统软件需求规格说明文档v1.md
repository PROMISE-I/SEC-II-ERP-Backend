# NJU-ERP 系统软件需求规格说明文档v1

[TOC]

## 引言

### 目的

本文档描述了 NJU-ERP 系统的功能需求和非功能需求。开发小组的软件系统实现与验证工作都以此文档为依据。

除特殊说明外，本文档所包含的需求都是高优先级需求。

### 范围

NJU-ERP 系统是为某民营企业开发的企业资源计划（ERP）系统，开发的目标是帮助该企业处理日常的重点业务，包括库存管理、销售管理、财务管理、人事管理和企业经营管理。

期望在系统上线 6 个月后，减少积压的库存，增加销售额，提高财务人员和人力资源人员工作效率，为经理的决策做支持。

### 参考文献

1. IEEE830-1998
2. NJU-ERP 系统用例文档v1

## 总体描述

### 商品前景

#### 背景与机遇

一民营企业专业从事灯具开关行业，是某著名开关品牌的南京地区总代理，主要在南京负责品牌的推广及项目的落地销售、分销的批发等工作，服务对象包括项目业主、施工单位、 分销商、设计院、终端用户等。

现公司规模扩大，企业业务量、办公场所、员工数都发生增长，为适应新的环境，提高工作效率和用户满意度，该公司聘南鲸软件科技公司开发一套 ERP 系统。该系统主要包括库存管理、销售管理、财务管理、人事管理和企业经营管理。

#### 业务需求

BR1：在系统上线 6 个月后，库存积压减少 30%。

BR2：在系统上线 6 个月后，销售额增加 20%。

BR3：在系统上线 6 个月后，运营成本要降低 20%。

  ​	范围：人力成本和库存成本。

  ​	度量：检查员工数量和单位销售额的平均库存成本。

BR4：在系统上线 6 个月后，财务人员和人力资源人员工作效率提高 50%。


### 商品功能

SF1：分析商品库存，发现可能存在的商品积压、缺货和报废现象。

SF2：根据市场变化调整销售的商品。

SF3：制定促销手段，处理挤压商品。

SF4：制定促销手段促进销售竞争。

SF5：掌握员工变动和授权情况。

SF6：处理商品的入库与出库。

SF7：帮助销售人员处理进货销售及相应退货业务。

### 用户特征

| 用户         | 特征                                                         |
| ------------ | ------------------------------------------------------------ |
| 库存管理人员 | 1 到 2 名。负责对商品分类和商品的信息的管理。能够简单使用办公信息化系统。对新系统基本持支持态度。不希望增加现有工作量。<br />工作细节包括商品分类管理、商品管理、库存查看、库存盘点、确认商品出入库等内容。 |
| 进货销售人员 | 4 到 8 名。负责完成进货单和销售单。对新系统基本持积极态度。不希望增加现有工作量。<br />销售人员分为销售员和销售经理<br />工作细节包括客户管理、制定进货单、制定进货退货单、制定销售单、制定销售退货单。<br />进货相关单据先有销售经理进行一级审批，再由总经理进行二级审批。 |
| 财务人员     | 1 到 2 名。使用办公信息化系统能力较强。对新系统持积极态度。不希望增加现有工作量。<br />工作细节包括账户管理、制定收款单、制定付款单、制定工资单、查看销售明细表、查看经营历程表、查看经营情况表、期初建账等内容。<br />财务相关的单据由总经理审批。 |
| 人力资源人员 | 1 到 2 名。使用办公信息化系统能力较强。对新系统持积极态度。不希望增加现有工作量。<br />工作细节包括员工管理、员工打卡、薪酬规则制定等内容。 |
| 总经理       | 2 名。能够熟练使用办公信息化系统。对新系统持积极态度。<br />工作细节包括制定促销策略、制定年终奖、查看销售明细表、查看经营历程表、查看经营情况表。 |
| 系统管理员   | 1 名。系统管理员是计算机专业维护人员，计算机技能很好。<br />系统管理员拥有所有用户的权限。 |

### 约束

CON1：系统将运行在 Windows 10 系统上。

CON2：系统不使用图形化界面，而是使用 Web 界面。

CON3：项目要使用持续集成方法进行开发。

CON4：在开发中，开发者要提交软件需求规格说明文档、设计描述文档和测试报告。

### 假设和依赖

AE1：在将上一批入库商品出库 90 %之前，下一批同类型商品不会被入库。

AE2：新一批商品的每天出库量与上一批商品每天出库量基本相同，商品出库情况比较稳定。

AE3：一个额度的赠送促销会自动包含所有比它小的额度的促销赠送商品。

## 详细需求描述

### 对外接口需求

#### 用户界面

用户使用 Web 界面，而不是图形化界面。

用户功能模块使用树状结构组织并展示。

#### 硬件接口

系统对硬件接口无特殊需求。

#### 软件接口

系统对软件接口无特殊需求。

#### 通信接口

CI：客户端使与服务器使用 REST 接口进行通信。

### 功能需求

#### 处理收款

##### 特性描述

对银行账户进行转账时制定收款单，交由总经理审核。

##### 刺激/响应序列

刺激：财务人员选择客户

响应：系统记录选择的客户

刺激：财务人员选择账户，输入转入金额，填写备注

响应：系统记录选择的账户、转入金额和备注

刺激：财务人员添加银行账户

响应：系统显示新的转账输入框

刺激：财务人员确认创建收款单

响应：系统存储收款单并等待审批

刺激：总经理审批或拒绝收款单

响应：系统记录收款单审批完成或失败

##### 相关功能需求

| 需求 ID                            | 需求描述                                                     |
| ---------------------------------- | ------------------------------------------------------------ |
| Collection.Input                   | 系统应该允许财务人员使用键盘输入                             |
| Collection.Input.Member            | 系统应该允许财务人员选择收款单对应的客户                     |
| Collection.Input.Account           | 系统应该允许财务人员选择收款单对应的银行账户                 |
| Collection.Input.Amount            | 系统应该允许财务人员选择收款单对应的金额                     |
| Collection.Input.AddBankAccount    | 系统应该允许财务人员增加银行账户                             |
| Collection.Input.DeleteBankAccount | 系统应该允许财务人员删除银行账户，但至少要保留一个银行账户   |
| Collection.Input.Cancel            | 系统应当允许财务人员创建收款单过程中取消创建                 |
| Collection.Input.Done              | 财务人员确认创建收款单后，系统应当保留相关信息，并交由总经理审批 |
| Collection.Approval                | 系统应当允许总经理审批收款单                                 |
| Collection.Update                  | 系统应当在完成创建和审批时更新收款单状态                     |

#### 处理付款

##### 特性描述

对银行账户进行转账时制定付款单，交由总经理审核。

##### 刺激/响应序列

刺激：财务人员选择客户

响应：系统记录选择的客户

刺激：财务人员选择账户，输入转出金额，填写备注

响应：系统记录选择的账户、转出金额和备注

刺激：财务人员添加银行账户

响应：系统显示新的转账输入框

刺激：财务人员确认创建付款

响应：系统存储付款单并等待审批

刺激：总经理审批或拒绝付款单

响应：系统记录付款单审批完成或失败

##### 相关功能需求

| 需求 ID                         | 需求描述                                                     |
| ------------------------------- | ------------------------------------------------------------ |
| Receipt.Input                   | 系统应该允许财务人员使用键盘输入                             |
| Receipt.Input.Member            | 系统应该允许财务人员选择付款单对应的客户                     |
| Receipt.Input.Account           | 系统应该允许财务人员选择付款单对应的银行账户                 |
| Receipt.Input.Amount            | 系统应该允许财务人员选择付款单对应的金额                     |
| Receipt.Input.AddBankAccount    | 系统应该允许财务人员增加银行账户                             |
| Receipt.Input.DeleteBankAccount | 系统应该允许财务人员删除银行账户，但至少要保留一个银行账户   |
| Receipt.Input.Cancel            | 系统应当允许财务人员创建付款单过程中取消创建                 |
| Receipt.Input.Done              | 财务人员确认创建付款单后，系统应当保留相关信息，并交由总经理审批 |
| Receipt.Approval                | 系统应当允许总经理审批付款单                                 |
| Receipt.Update                  | 系统应当在完成创建和审批时更新付款单状态                     |

#### 处理工资发放

##### 特性描述

使用银行账户向员工发放工资时，进行工资单制定和审批。

##### 刺激/响应序列

刺激：人力资源人员选择员工

响应：系统记录选择的员工

刺激：人力资源人员选择账户

响应：系统记录选择的账户

刺激：人力资源人员添加银行账户

响应：系统显示新的转账输入框

刺激：人力资源人员确认创建付款

响应：系统存储付款单并等待审批

刺激：总经理审批或拒绝付款单

响应：系统记录付款单审批完成或失败

##### 相关功能需求

| 需求 ID                  | 需求描述                                                     |
| ------------------------ | ------------------------------------------------------------ |
| Salary.Input             | 系统应该允许财务人员使用键盘输入                             |
| Salary.Input.Staff       | 系统应该允许财务人员选择工资单对应的员工                     |
| Salary.Input.BankAccount | 系统应该允许财务人员选择工资单对应的银行账户                 |
| Salary.Input.Cancel      | 系统应当允许财务人员创建工资单过程中取消创建                 |
| Salary.Input.Done        | 财务人员确认创建工资单后，系统应当保留相关信息，并交由总经理审批 |
| Salary.Approval          | 系统应当允许总经理审批工资单                                 |
| Salary.Update            | 系统应当在完成创建和审批时更新工资单状态                     |

#### 查看销售明细表

##### 特性描述

查看销售明细表（统计一段时间内商品的销售情况（包括销售和销售后退货），筛 选条件有：时间区间，商品名，客户，业务员，仓库。显示符合上述条件的所有商 品销售记录，以列表形式显示，列表中包含如下信息：时间（精确到天），商品名，
型号，数量，单价，总额。需要支持导出数据。）

##### 刺激/响应序列

刺激：用户选择时间段

响应：系统记录选择的时间段

刺激：用户选择商品名

响应：系统记录选择的商品名

刺激：用户选择客户

响应：系统记录选择的客户

刺激：用户选择操作员

响应：系统记录选择的操作员

刺激：用户确认查询

响应：系统显示查询的销售记录合计、销售退货数量合计、出入库商品信息

##### 相关功能需求

| 需求 ID                        | 需求描述                                           |
| ------------------------------ | -------------------------------------------------- |
| SalesDetails.Input             | 系统应该允许财务人员使用键盘输入                   |
| SalesDetails.Input.Period      | 系统应该允许财务人员选择查看的时间段               |
| SalesDetails.Input.ProductName | 系统应该允许财务人员选择查看的商品名               |
| SalesDetails.Input.Client      | 系统应该允许财务人员选择查看的客户                 |
| SalesDetails.Input.Operator    | 系统应该允许财务人员选择查看的操作员               |
| SalesDetails.Input.Done        | 财务人员确认查询销售明细表后，系统应该显示销售明细 |

#### 查看经营历程表

##### 特性描述

查看经营历程表，查看一段时间里的所有单据，单据分为：

1. 销售类单据（销售出 货单，销售退货单）
2. 进货类单据（进货单，进货退货单）
3. 财务类单据（付款单，收款单，现金费用单，工资单）
4. 库存类单据（报溢单，报损单，赠送单）。

 筛选条件为：时间区间，单据类型，客户，业务员，仓库。显示出符合条件的单据后，可以对单据进行查看操作，但是不可修改和删除。

##### 刺激/响应序列

刺激：用户选择时间段

响应：系统记录选择的时间段

刺激：用户选择单据类型

响应：系统记录选择的单据类型，并提示用户选择相应筛选信息

刺激：用户选择相应筛选信息

刺激：用户确认查询

响应：系统显示查询的单据合计和具体单据信息。

##### 相关功能需求

| 需求 ID                         | 需求描述                                           |
| ------------------------------- | -------------------------------------------------- |
| BusinessHistory.Input           | 系统应该允许财务人员使用键盘输入                   |
| BusinessHistory.Input.Period    | 系统应该允许财务人员选择查看的时间段               |
| BusinessHistory.Input.SheetType | 系统应该允许财务人员选择单据类型                   |
| BusinessHistory.Input.Done      | 财务人员确认查询销售明细表后，系统应该显示经营历程 |

#### 查看经营情况表

##### 特性描述

查看经营情况表，统计显示一段时间内的经营收支状况和利润。经营收入显示为折 让后，并显示出折让了多少。显示信息：

1. 收入类：销售收入、商品类收入（商品报溢收入成本调价收入进货退货差价代金券与实际收款差额收入）。收入类显示折让后总收入，并显示折让了多少。
2. 支出类：销售成本、商品类支出（商品报损 商品赠出）、人力成本。支出类显示总支出。
3. 利润：折让后总收入-总支出。

##### 刺激/响应序列

刺激：用户选择时间段。

响应：系统显示查询的折让后总收入合计、折让总额合计、总支出合计、利润合计。

##### 相关功能需求

| 需求 ID                         | 需求描述                                                     |
| ------------------------------- | ------------------------------------------------------------ |
| BusinessConditions.Input        | 系统应该允许财务人员使用键盘输入                             |
| BusinessConditions.Input.Period | 系统应该允许财务人员选择查看的时间段                         |
| BusinessConditions.Display      | 财务人员查看经营情况时，系统应当显示折让后总收入、折让总额、总支出合计、利润合计 |

#### 员工打卡

##### 特性描述

所有员工，每天需要登录系统进行打卡。缺席扣除基本工资的 1/30。总经理不参与打卡。

##### 刺激/响应序列

刺激：用户请求“今日打卡”

响应：系统记录该用户今日打卡

刺激：用户重复请求“今日打卡”

响应：系统提示“今天已经打过卡了，明天再来吧~”

##### 相关功能需求

| 需求 ID           | 需求描述                           |
| ----------------- | ---------------------------------- |
| CheckIn.CheckIn   | 系统应该允许并记录员工每日首次打卡 |
| CheckIn.RecheckIn | 系统应该提示并忽略员工每日重复打卡 |

#### 岗位管理

##### 特性描述

人力资源人员设置岗位对应的基本工资、岗位工资、岗位级别、薪资计算方式、薪资发放方式、扣税信息等制度信息。

##### 刺激/响应序列

刺激：人力资源人员请求编辑岗位信息

响应：系统显示岗位名称、基本工资、岗位工资、岗位级别、薪资计算方式、薪资发放方式、扣税信息

刺激：人力资源人员修改基本工资、岗位工资、岗位级别、扣税信息

响应：系统记录人力资源人员的修改

刺激：人力资源人员确认修改

响应：系统记录人力资源人员的修改

##### 相关功能需求

| 需求 ID                | 需求描述                                                     |
| ---------------------- | ------------------------------------------------------------ |
| Post.Input             | 系统应该允许人力资源人员使用键盘输入                         |
| Post.Input.BasedSalary | 系统应该允许人力资源人员编辑基本工资                         |
| Post.Input.PostSalary  | 系统应该允许人力资源人员编辑岗位工资                         |
| Post.Input.PostLevel   | 系统应该允许人力资源人员编辑岗位级别                         |
| Post.Input.Tax         | 系统应该允许人力资源人员编辑扣税信息                         |
| Post.Input.Done        | 人力资源人员确认修改岗位信息后，系统应当保存人力资源人员的修改 |

#### 制定促销策略

##### 特性描述

制定促销策略：

1. 总经理可以针对不同级别的用户制定促销策略（赠品、价格折让、赠送代金劵）。
2. 总经理可以制定特价包（组合商品降价）。
3. 总经理可以制定 针对不同总价的促销策略（赠品、赠送代金卷）。所有促销策略都有其实时间和间隔时间。所有赠品条件促发后，系统会自动建立库存赠送单，由总经理审批通过后，发送消息给库存管理员发放赠品。）

##### 刺激/响应序列

**制定基于总价的促销策略**

刺激：人力资源人员请求制定基于总价的促销策略

响应：系统提示总经理选择促销时间段、输入总价阈值、输入优惠券金额

刺激：总经理选择促销时间段、输入总价阈值、输入优惠券金额

响应：系统提示总经理选择赠品的商品 id、商品数量、商品单价、赠品备注

刺激：总经理选择赠品的商品 id、商品数量、商品单价、赠品备注

响应：系统提示总经理填写促销策略备注

刺激：总经理确认创建基于总价的促销策略

响应：系统记录创建的基于总价的促销策略

**制定基于组合的促销策略**

刺激：总经理请求制定新的促销策略

响应：系统提示总经理选择促销时间段、折让金额、商品

刺激：总经理选择促销时间段、折让金额、商品

刺激：总经理确认创建基于总价的促销策略

响应：系统记录创建的基于总价的促销策略

**制定基于用户级别的促销策略**

刺激：总经理请求编辑某用户级别对应的促销策略

响应：系统提示总经理选择促销时间段、输入折让比例、输入代金券、选择赠品并输入数量

刺激：总经理选择促销时间段、输入折让比例、输入代金券、选择赠品并输入数量

刺激：若有多种赠品，总经理请求添加赠品

响应：系统提示选择新的赠品并输入新的赠品数量

刺激：总经理确认修改用户级别促销策略

响应：系统保存本次修改

##### 相关功能需求

| 需求 ID                            | 需求描述                                                     |
| ---------------------------------- | ------------------------------------------------------------ |
| Promotion.Input                    | 系统应该允许总经理使用键盘输入                               |
| Promotion.Input.Period             | 系统应该允许总经理选择促销时间段                             |
| Promotion.Input.Threshold          | 系统应该允许总经理输入基于总价的促销策略的总价阈值           |
| Promotion.Input.Coupon             | 系统应该允许总经理输入基于总价的促销策略和基于用户级别的促销策略的优惠券金额 |
| Promotion.Input.Gift               | 系统应该允许总经理输入基于总价的促销策略和基于用户级别的促销策略的赠品 |
| Promotion.Input.DiscountAmount     | 系统应该允许总经理输入基于组合的促销策略的折让金额           |
| Promotion.Input.ProductCombination | 系统应该允许总经理输入基于组合的促销策略的商品组合           |
| Promotion.Input.DiscountRatio      | 系统应该允许总经理输入基于用户级别的促销策略的折让比例       |
| Promotion.Input.Remark             | 系统应该允许总经理输入促销策略的备注                         |
| Promotion.Input.Done               | 人力资源人员确认修改岗位信息后，系统应当保存人力资源人员的修改 |

#### 制定年终奖

##### 特性描述

根据个人每年综合表现制定年终奖。可以查看之前 11 个月的工资收入总和，避免 陷入税务陷阱（发的多，但由于税率提高，拿的少）。

##### 刺激/响应序列

刺激：总经理选择员工

响应：系统记录选择的员工

刺激：总经理查询前 11 月工资

响应：系统显示前 11 月工资

刺激：总经理输入年终奖金额

响应：系统记录年终奖金额

刺激：总经理确认制定年终奖

响应：系统保存年终奖

##### 相关功能需求

| 需求 ID                  | 需求描述                                       |
| ------------------------ | ---------------------------------------------- |
| Awards.Input             | 系统应该允许总经理使用键盘输入                 |
| Awards.Input.Staff       | 系统应该允许总经理选择员工                     |
| Awards.Input.AwardAmount | 系统应该允许总经理输入年终奖金额               |
| Awards.Input.QuerySalary | 系统应该允许总经理查询员工前 11 月工资         |
| Awards.Input.Done        | 总经理确认制定年终奖后，系统应当保存并做出更改 |

### 非功能需求

#### 安全性

Safety1：系统应该只允许经过验证和授权的用户访问。

Safety2：系统应该按照用户身份验证用户的访问权限。

Safety3：系统中有一个默认的管理员账户，该账户只允许管理员用户修改口令。

#### 可维护性

Modifiability1：如果系统要增加新的促销策略，要能够在 0.25 个人月内完成。

Modifiability2：如果系统要增加新的或修改原有的薪酬规则，要求能够在 0.25 个人月内完成。

#### 易用性

Usability1：用户能够在使用系统 3 天后熟练操作系统，出错率降低到 1% 以下。

Usability2：库存管理页面能够以可视化的形式展示商品库存。

#### 可靠性

Reliability1：因网络故障而不能正常和服务器通信时，用户能够被告知请求的操作未被应答。

Reliability2：网络故障恢复后，系统应当继续未完成的工作。

#### 业务规则

1. 年终奖金额由总经理根据员工综合表现决定。
2. 基于总价的促销策略：一定时间段内，总价达到某一阈值后，赠送优惠券和赠品。
3. 基于组合的促销策略：购买包含一定商品的特价包时，组合商品降价。
4. 基于用户级别的促销策略：针对不同级别的客户，提供不同级别的赠品、价格折让、赠送代金券等促销策略。

#### 约束

IC：系统前后端要部署在同一个服务器。

### 数据需求

#### 数据定义

DR1：系统需要存储的数据参见体系结构文档。

DR2：系统需要存储 1 年内的进货记录和销售记录。

#### 默认数据

默认数据用于以下情况：

1. 系统新增加未初始化数据时。
2. 编辑数据时将相关数据内容清空时。

默认数据内容为：

Default1：年终奖的年份默认为服务器时间的上一个 12 月所在的年份

#### 数据格式要求

Format1：进货单编号为：JHD-yyyyMMdd-xxxxx，后五位从 1 开始编号。

Format2：进货退货单编号为：JHTHD-yyyyMMdd-xxxxx，后五位从 1 开始编号。

Format3：销售单编号为：XSD-yyyyMMdd-xxxxx，后五位从 1 开始编号。

Format4：销售退货单编号为：XSTHD-yyyyMMdd-xxxxx，后五位从 1 开始编号。

Format5：收款单编号为：SKD-yyyyMMdd-xxxxx，后五位从 1 开始编号。

Format6：工资单编号为：GZD-yyyyMMdd-xxxxx，后五位从 1 开始编号。

#### 其他需求

系统无其他需求。
