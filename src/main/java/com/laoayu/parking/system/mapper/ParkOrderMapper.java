package com.laoayu.parking.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laoayu.parking.system.entity.ParkOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author laoayu
 * @since 2023-03-18
 */
public interface ParkOrderMapper extends BaseMapper<ParkOrder> {

    Page<ParkOrder> getParkOrderList(Page<ParkOrder> page, String plateColor, String type, String parkName, String userName);

    ParkOrder getByPlateNumber(String plateNum, Long parkId);

    List<ParkOrder> selectParkOrderByParkIdAndPlateNum(Long parkId, String plateNum);

    BigDecimal getTotalIncome(String userName);
    /**
     * 查询指定日期范围内每日缴费金额
     * @param params 查询参数，包含 startDate 和 endDate
     * @return 每日缴费金额列表
     */
    List<Map<String, Object>> getDailyPayments(Map<String, Object> params);}
