package com.zsbatech.baasKettleManager.dao;


import com.zsbatech.baasKettleManager.model.TableOutputMetaDO;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/20
 */
@Repository
public interface TableOutputMetaDOMapper {

    int insert(TableOutputMetaDO tableOutputMetaDO);

    TableOutputMetaDO selectTableOutputMetaVO(String stepName);

    TableOutputMetaDO selectTableOutputMetaVOByTransMetaId(int transMetaId);
}
