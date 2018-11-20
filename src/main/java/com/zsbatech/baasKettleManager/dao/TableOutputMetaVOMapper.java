package com.zsbatech.baasKettleManager.dao;


import com.zsbatech.baasKettleManager.vo.TableOutputMetaVO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/20
 */
public interface TableOutputMetaVOMapper {

    int insert(TableOutputMetaVO tableOutputMetaVO);

    TableOutputMetaVO selectTableOutputMetaVO(String stepName);

    TableOutputMetaVO selectTableOutputMetaVOByTransMetaId(int transMetaId);
}
