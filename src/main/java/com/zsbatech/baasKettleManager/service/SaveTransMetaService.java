package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.InsertUpdateStepDO;
import com.zsbatech.baasKettleManager.model.TableInputStepDO;
import com.zsbatech.baasKettleManager.model.TableOutputMetaDO;
import com.zsbatech.baasKettleManager.model.TransMetaDO;
import org.pentaho.di.core.EngineMetaInterface;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018-11-15
 */
public interface SaveTransMetaService {

    boolean save(EngineMetaInterface meta, String filename, boolean export );

    boolean saveMeta( EngineMetaInterface meta, String filename );

    boolean saveTransData(String fileName,int srcDbConnId,int dstDbConnId);

    TransMetaDO getTransMetaDO(TransMeta transMeta);

    List<TableInputStepDO> getTableInputStepDO(TransMeta transMeta);

    List<TransHopMeta> getTransHopMetas(TransMeta transMeta);

    TableOutputMetaDO getTableOutputMetaDO(TransMeta transMeta);

    InsertUpdateStepDO getInsertUpdateStepDO(TransMeta transMeta);

    boolean saveByDB(String name , String[] fields);
}
