package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.ExceptionLogVO;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/12/10
 */
@Repository
public interface ExceptionLogVOMapper {

    int insert(ExceptionLogVO exceptionLogVO);
}
