package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.ExceptionLogDO;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/12/10
 */
@Repository
public interface ExceptionLogDOMapper {

    int insert(ExceptionLogDO exceptionLogDO);
}
