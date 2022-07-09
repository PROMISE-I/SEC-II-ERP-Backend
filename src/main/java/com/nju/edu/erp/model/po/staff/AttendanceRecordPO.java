package com.nju.edu.erp.model.po.staff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * PO for attendance check
 * @author sunlifan
 * @date 2022/7/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRecordPO {
    /**
     * staff id, unique for each staff
     */
    Integer staffId;

    /**
     * date, record when staff clock in
     */
    String date;
}
