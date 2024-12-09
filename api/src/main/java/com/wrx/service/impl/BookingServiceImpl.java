package com.wrx.service.impl;

import com.wrx.entity.Booking;
import com.wrx.mapper.BookingMapper;
import com.wrx.service.BookingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 预约挂号表 服务实现类
 * </p>
 *
 * @author 一只会喵的布偶猫
 * @since 2024-12-07
 */
@Service
public class BookingServiceImpl extends ServiceImpl<BookingMapper, Booking> implements BookingService {

}
