package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.BusinessHistorySheetVO;

import java.util.List;

public interface BusinessHistoryService {
    public List<BusinessHistorySheetVO> findALl(String begin, String end);
}
