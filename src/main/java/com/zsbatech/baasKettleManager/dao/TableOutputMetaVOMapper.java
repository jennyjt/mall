package com.zsbatech.baasKettleManager.dao;


import com.zsbatech.baasKettleManager.vo.TableOutputMetaVO;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/20
 */
@Repository
public interface TableOutputMetaVOMapper {

    int insert(TableOutputMetaVO tableOutputMetaVO);

    TableOutputMetaVO selectTableOutputMetaVO(String stepName);

    TableOutputMetaVO selectTableOutputMetaVOByTransMetaId(int transMetaId);
}
