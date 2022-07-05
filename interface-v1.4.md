
## 账户
- 增加账户 Response：名称、金额
- 删除账户 Response：名称
- 修改账户属性 Response：名称
- <font color=blue>findAll</font> 
- <font color=blue>findById</font>
- 查询 List<BankAccount>：名称、可以模糊
- BankAccount: 名称、金额

## 收款单
- 新建收款单 Response：客户、操作员、转账列表（银行账户、转账金额、备注）
- 查询收款单 List <ReceiveMoneyReceipt>：状态
- <font color=blue>findAll</font>
- ReceiveMoneyReceipt：单据编号，客户，操作员，转账列表，总额汇总。
- 转账列表：银行账户、转账金额

## 付款单：
- 新建付款单 Response：客户、操作员、转账列表（银行账户、转账金额、备注）
- 查询付款单 List <PayMoneyReceipt>：状态
- <font color=blue>findAll</font>
- PayMoneyReceipt: 和收款单一样

## 工资单
- 员工：Staff
- 查询某个员工某个月的工资 Int：员工、时间（月）
- <font color=blue>findAll</font>
- 新建工资单 Response：员工编号、银行账户信息（账户：名称、金额）
- 查询工资单 List <SalaryReceipt>：状态
- SalaryReceipt: 工资单编号、员工编号、姓名、银行账户信息、应发工资、扣除税款、实发金额

## 销售明细表
- 查询销售表 List<SaleSheet>：时间区间，商品名，客户，业务员
- 查询销售退货表 List<SaleReturnsSheet>：时间区间，商品名，客户，业务员
- SaleSheet, SaleReturnSheet：写好了

## 经营历程表
- <font color=blue>findAll</font> ：这个要全部的表
- 查询经营历程表 List<经营历程表>：时间区间，单据类型（销售类、进货类、财务类、库存类）
- 经营历程表: type, Receipt (interface)

## 经营情况表
- 查询经营情况表 List <经营情况表>：时间区间
- 经营情况表：销售收入、商品类收入，销售成本、商品类支出、利润

## 员工信息（抄客户管理）
- 新增员工信息 Response：姓名、性别、出生日期、手机、工作岗位、工资卡账户。
- 删除员工信息 Response：员工编号
- 查询员工信息（findAll） List <Staff>
- 修改员工信息 Response：员工编号，姓名、性别、出生日期、手机、工作岗位、工资卡账户。
- <font color=blue>findById</font>
- Staff: 编号、姓名、性别、出生日期、手机、工作岗位、工资卡账户

## 岗位信息
- 修改岗位信息 Response：名称，基本工资、岗位工资、岗位级别、薪资计算方式、薪资发放方式、扣税信息。
- 查询所有岗位 岗位：findall
- 查询岗位信息 岗位信息：岗位名称
- <font color=blue>把这个写死的东西整到数据库里面去</font>
- 岗位信息：名称，基本工资、岗位工资、岗位级别、薪资计算方式、薪资发放方式、扣税信息。

## 打卡信息
- 打卡 Response：用户、时间（天）
- 查询打卡状态 Boolean：用户、时间（天)

-------

## 年终奖

- 新的表：员工，年份，年终奖
- findAllStaff List<StaffVO>（排除总经理）
- findStaffById StaffVO：staffId
- setYearFinal Response: staffId，yearFinal
- 年终奖：默认 0

# 促销策略
## 针对不同级别用户

- findAll List<针对不同级别的促销策略>
- findByLevel 针对不同级别的促销策略：
- update ：一整个类
- 针对不同级别的促销策略：level，(赠品)List<ProductInfoVO>，价格折让，代金券，开始时间，结束时间
- 默认值

## 组合商品特价包

- findAll 
- findByPair<Pro1, Pro2>
- create
- update
- delete
- 开始时间，结束时间
- 以代金券的形式实现，约定一次只能使用一个特价包，一个特价包仅有两个商品

## 针对总价的促销策略

- findAll
- findById
- create
- update
- delete
- List<ProductInfoVO>、代金券 开始时间，结束时间
- 约定一次只能使用一个针对总价的促销策略，取额度最大的

# 计算优惠不考虑赠品
- 计算优惠的时候不考虑赠品，单纯从数额上计算最大优惠，赠品是附带的

