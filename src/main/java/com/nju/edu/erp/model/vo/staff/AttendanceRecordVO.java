package com.nju.edu.erp.model.vo.staff;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * VO for attendance check
 * @author sunlifan
 * @date 2022/7/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRecordVO {
    /**
     * staff Id, unique for each staff
     */
    Integer staffId;

    /**
     * date, record when staff clock in
     */
    String date;
}
