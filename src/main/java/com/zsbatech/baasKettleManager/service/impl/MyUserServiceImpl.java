package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.AddrMapper;
import com.zsbatech.baasKettleManager.dao.MyUserMapper;
import com.zsbatech.baasKettleManager.model.Addr;
import com.zsbatech.baasKettleManager.model.MyUser;
import com.zsbatech.baasKettleManager.model.MyUserExample;
import com.zsbatech.baasKettleManager.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MyUserServiceImpl implements MyUserService {

	@Autowired
	private MyUserMapper userMapper;
	@Autowired
	private AddrMapper addrMapper;
	@Override
	public boolean add(MyUser user) {
		// TODO Auto-generated method stub
		user.setCreatTime(new Date());
		user.setUpdataTime(new Date());
		int a = userMapper.insert(user);

		if (a>0) {
			return true;
		}
		return false;
	}
//	@Override
//	public Pagination<DbManagement> getList(Integer currPage, Integer pageSize, MyUser user) {
//		PageMethod.startPage(currPage, pageSize);
//		user.setStatus(DataSourceConstant.NORMAL_STATUS);
//		List<DbManagement> dataSourceList = userMapper.getDbManagentsByParam(user);
//		Pagination<DbManagement> dataSourceInfo = new Pagination<DbManagement>(dataSourceList);
//		return dataSourceInfo;
//	}

	@Override
	public List<MyUser> find(MyUser user) {
		MyUserExample userExample = new MyUserExample();
		MyUserExample.Criteria criteria = userExample.createCriteria();
		criteria.andAgeEqualTo(user.getAge());
		criteria.andTelephotoEqualTo(user.getTelephoto());
		List<MyUser> userList = userMapper.selectByExample(userExample);
//		System.out.println(userList);
//		if(userList.size()>0){
//			return true;
//		}
//		return false;
//	}
		return userList;
	}
	

}
