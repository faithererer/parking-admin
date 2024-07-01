package com.laoayu.parking.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laoayu.parking.system.entity.ParkOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单信息表 服务类
 * </p>
 *
 * @author laoayu
 * @since 2023-03-18
 */
public interface IParkOrderService extends IService<ParkOrder> {

    Page<ParkOrder> getParkOrderList(Page<ParkOrder> page, String plateColor, String type, String parkName, String userName);

    /**
     * 根据 车牌号和停车场id查询车辆是否入库
     * @param plateNum
     * @param parkId
     * @return
     */
    ParkOrder getByPlateNumber(String plateNum, Long parkId);

    /**
     * 根据 parkId plateNum 查询未完成订单
     * @param parkId
     * @param plateNum
     * @return
     */
    ParkOrder selectParkOrderByParkIdAndPlateNum(Long parkId, String plateNum);

    /**
     * 根据id删除订单
     * @param id
     */
    void deleteParkOrderById(Long id);

    /**
     * 计算总收入
     * @param userName
     * @return
     */
    BigDecimal getTotalIncome(String userName);

    List<Map<String, Object>> getDailyPayments(String startDate, String endDate);
    /**
     * 获取每日缴费金额列表
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 每日缴费金额列表
     */


}
