package com.markerhub.service.impl;

import com.markerhub.entity.Booking;
import com.markerhub.mapper.BookingMapper;
import com.markerhub.service.BookingService;
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
