package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.TableInputStepDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/19
 */
@Repository
public interface TableInputStepDOMapper {

    int insertBatch(List<TableInputStepDO> tableInputStepDOList);

    List<TableInputStepDO> selectTableInputStepVO(List<String> stepNameList);

    List<TableInputStepDO> selectTableInputStepVOS(int transMetaId);
}
