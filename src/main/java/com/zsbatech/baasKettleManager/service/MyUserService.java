package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.MyUser;

import java.util.List;

public interface MyUserService {

	boolean add(MyUser user);
	List<MyUser> find(MyUser user);
}
