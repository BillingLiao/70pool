package com.ant.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.UserDao;
import com.ant.admin.service.UserService;
import com.ant.entity.User;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<User> page = this.selectPage(
                new Query<User>(params).getPage(),
                new EntityWrapper<User>()
        );

        return new PageUtils(page);
    }
    
    @Override
    public PageUtils queryPage(Map<String, Object> params,User user) {
    	 
     	EntityWrapper ew =new EntityWrapper<User>();
     	Map<String, Object> map = BeanUtil.beanToMap(user, true, true);
     	map.forEach((k,v)->{
      		ew.like(k, v.toString());
     	});
          Page<User> page = this.selectPage(
                new Query<User>(params).getPage(),
               ew
        );

        return new PageUtils(page);
    }
	public PageUtils queryPage(Map<String, Object> params, Wrapper<User> wrapper) {
		  Page<User> page =new Query<User>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;

 	}
    
    @Override
	public List<User> selectListVO( Wrapper<User> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public User selectVO( Wrapper<User> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<User> selectListView(Wrapper<User> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public User selectView(Wrapper<User> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
