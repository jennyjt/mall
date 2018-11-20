package com.zsbatech.baasKettleManager.service;

import org.pentaho.di.core.EngineMetaInterface;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018-11-15
 */
public interface SaveJobMetaService {
    boolean save(EngineMetaInterface meta, String filename, boolean export );

    boolean saveMeta( EngineMetaInterface meta, String filename );
}
