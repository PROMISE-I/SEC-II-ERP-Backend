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

### 业务逻辑层的分解

业务逻辑层的开发包图见体系结构文档。

#### 销售模块

##### 单据的状态图

![status-diagram](NJU-ERP%E7%B3%BB%E7%BB%9F%E8%AF%A6%E7%BB%86%E8%AE%BE%E8%AE%A1%E6%96%87%E6%A1%A3v1/201250127-status-diagram.jpg)

##### 查看经营历程表的系统顺序图

<img src="NJU-ERP%E7%B3%BB%E7%BB%9F%E8%AF%A6%E7%BB%86%E8%AE%BE%E8%AE%A1%E6%96%87%E6%A1%A3v1/201250185-%E6%9F%A5%E7%9C%8B%E7%BB%8F%E8%90%A5%E5%8E%86%E7%A8%8B%E8%A1%A8%E7%B3%BB%E7%BB%9F%E9%A1%BA%E5%BA%8F%E5%9B%BE.png" alt="查看经营历程表系统顺序图" style="zoom:70%;" />

##### 销售模块的概念类图

<img src="NJU-ERP%E7%B3%BB%E7%BB%9F%E8%AF%A6%E7%BB%86%E8%AE%BE%E8%AE%A1%E6%96%87%E6%A1%A3v1/201250181-class-diagram.png" alt="class-diagram" style="zoom:80%;" />



## 依赖视角

NJU-ERP 系统开发包图（https://processon.com/diagraming/627093086376891e1c2106c1）