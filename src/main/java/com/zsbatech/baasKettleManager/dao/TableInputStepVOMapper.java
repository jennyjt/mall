package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.TableInputStepVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/19
 */
@Repository
public interface TableInputStepVOMapper {

    int insertBatch(List<TableInputStepVO> tableInputStepVOList);

    List<TableInputStepVO> selectTableInputStepVO(List<String> stepNameList);

    List<TableInputStepVO> selectTableInputStepVOS(int transMetaId);
}
