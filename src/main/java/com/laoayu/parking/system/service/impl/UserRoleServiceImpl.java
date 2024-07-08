package com.laoayu.parking.system.service.impl;

import com.laoayu.parking.system.entity.Role;
import com.laoayu.parking.system.entity.UserRole;
import com.laoayu.parking.system.mapper.UserRoleMapper;
import com.laoayu.parking.system.service.IRoleService;
import com.laoayu.parking.system.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author laoayu
 * @since 2023-03-07
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Autowired
    private IRoleService roleService;

    @Override
    public Role getRoleByUserId(Long userId) {
        System.out.println("权限请求:"+userId);
        UserRole userRole = query().eq("user_id", userId).one();
        Role role = roleService.getRoleById(userRole.getRoleId());
        return role;
    }

}
