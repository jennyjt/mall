package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.vo.FTPDownLoadStepVO;
import com.zsbatech.baasKettleManager.vo.FTPPutStepVO;
import com.zsbatech.baasKettleManager.vo.JobMetaVO;
import com.zsbatech.baasKettleManager.vo.JobStartStepVO;
import org.pentaho.di.core.EngineMetaInterface;
import org.pentaho.di.job.JobMeta;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018-11-15
 */
public interface SaveJobMetaService {
    boolean save(EngineMetaInterface meta, String filename, boolean export);

    boolean saveMeta(EngineMetaInterface meta, String filename);

    JobMetaVO getJobMetaVO(JobMeta jobMeta);

    JobStartStepVO getJobStartStepVO(JobMeta jobMeta);

    FTPPutStepVO getFTPPutStepVO(JobMeta jobMeta);

    FTPDownLoadStepVO getFTPDownLoadStepVO(JobMeta jobMeta);

    boolean saveByDB(String name);
}
