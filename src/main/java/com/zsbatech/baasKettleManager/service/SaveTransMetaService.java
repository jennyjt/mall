package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.vo.TableOutputMetaVO;
import com.zsbatech.baasKettleManager.vo.TableInputStepVO;
import com.zsbatech.baasKettleManager.vo.TransMetaVO;
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

    TransMetaVO getTransMetaVO(TransMeta transMeta);

    List<TableInputStepVO> getTableInputStepVO(TransMeta transMeta);

    List<TransHopMeta> getTransHopMetas(TransMeta transMeta);

    TableOutputMetaVO getTableOutputMetaVO(TransMeta transMeta);

    boolean saveByDB(String name , String[] fields);
}
