package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.vo.*;
import org.pentaho.di.core.EngineMetaInterface;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.trans.JobEntryTrans;

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

    boolean saveFTPJobData(String fileName);

    boolean saveTransJobData(String fileName);

    JobMetaVO getJobMetaVO(JobMeta jobMeta);

    JobStartStepVO getJobStartStepVO(JobMeta jobMeta);

    FTPPutStepVO getFTPPutStepVO(JobMeta jobMeta);

    FTPDownLoadStepVO getFTPDownLoadStepVO(JobMeta jobMeta);

    JobEntryTrans getJobEntryTrans(JobMeta jobMeta);

    boolean saveByDB(String name);
}
