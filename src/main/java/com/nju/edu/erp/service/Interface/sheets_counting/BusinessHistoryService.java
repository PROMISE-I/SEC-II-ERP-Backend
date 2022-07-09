package com.nju.edu.erp.service.Interface.sheets_counting;

import com.nju.edu.erp.model.vo.sheets_counting.BusinessHistoryQueryVO;
import com.nju.edu.erp.model.vo.sheets_counting.BusinessHistorySheetVO;

import java.util.List;

public interface BusinessHistoryService {
    public List<BusinessHistorySheetVO> findAll(BusinessHistoryQueryVO businessHistoryQueryVO);
}
