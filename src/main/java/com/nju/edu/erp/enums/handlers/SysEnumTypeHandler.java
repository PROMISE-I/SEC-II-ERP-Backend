package com.nju.edu.erp.enums.handlers;

import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.customer.CustomerType;
import com.nju.edu.erp.enums.salary_strategy.SalaryCalculateType;
import com.nju.edu.erp.enums.salary_strategy.SalarySendType;
import com.nju.edu.erp.enums.sheet_state.finance.PayMoneySheetState;
import com.nju.edu.erp.enums.sheet_state.finance.ReceiveMoneySheetState;
import com.nju.edu.erp.enums.sheet_state.finance.SalarySheetState;
import com.nju.edu.erp.enums.sheet_state.gifts.GiveAwaySheetState;
import com.nju.edu.erp.enums.sheet_state.purchase.PurchaseReturnsSheetState;
import com.nju.edu.erp.enums.sheet_state.purchase.PurchaseSheetState;
import com.nju.edu.erp.enums.sheet_state.sale.SaleReturnsSheetState;
import com.nju.edu.erp.enums.sheet_state.sale.SaleSheetState;
import com.nju.edu.erp.enums.sheet_state.warehouse.WarehouseInputSheetState;
import com.nju.edu.erp.enums.sheet_state.warehouse.WarehouseOutputSheetState;
import org.apache.ibatis.type.MappedTypes;

/**
 * 枚举转换的公共模块
 *
 */
@MappedTypes(value = {PurchaseSheetState.class, WarehouseInputSheetState.class,
        WarehouseOutputSheetState.class, CustomerType.class,
        SaleSheetState.class, PurchaseReturnsSheetState.class,
        SaleReturnsSheetState.class, PayMoneySheetState.class,
        ReceiveMoneySheetState.class, SalarySheetState.class,
        SalaryCalculateType.class, SalarySendType.class,
        GiveAwaySheetState.class})
public class SysEnumTypeHandler<E extends Enum<E> & BaseEnum> extends BaseEnumTypeHandler<E> {
    /**
     * 设置配置文件设置的转换类以及枚举类内容，供其他方法更便捷高效的实现
     *
     * @param type 配置文件中设置的转换类
     */
    public SysEnumTypeHandler(Class<E> type) {
        super(type);
    }
}