package com.funtl.spring.security.oauth2.server.service.impl;

import com.funtl.spring.security.oauth2.server.domain.TbPermission;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.funtl.spring.security.oauth2.server.mapper.TbPermissionMapper;
import com.funtl.spring.security.oauth2.server.service.TbPermissionService;

import java.util.List;

@Service
public class TbPermissionServiceImpl implements TbPermissionService{

    @Resource
    private TbPermissionMapper tbPermissionMapper;

    @Override
    public List<TbPermission> selectByUserId(Long userId) {
        return tbPermissionMapper.selectByUserId(userId);
    }
}
