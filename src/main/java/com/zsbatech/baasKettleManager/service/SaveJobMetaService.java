package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.FTPDownLoadStepDO;
import com.zsbatech.baasKettleManager.model.FTPPutStepDO;
import com.zsbatech.baasKettleManager.model.JobMetaDO;
import com.zsbatech.baasKettleManager.model.JobStartStepDO;
import com.zsbatech.baasKettleManager.vo.*;
import org.pentaho.di.core.EngineMetaInterface;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.trans.JobEntryTrans;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018-11-15
 */
public interface SaveJobMetaService {
    boolean save(EngineMetaInterface meta, String filename, boolean export);

    boolean saveMeta(EngineMetaInterface meta, String filename);

    boolean saveFTPJobData(String fileName,String upSourceNickName,String downSourceNickName);

    boolean saveTransJobData(String fileName);

    JobMetaDO getJobMetaDO(JobMeta jobMeta);

    JobStartStepDO getJobStartStepDO(JobMeta jobMeta);

    FTPPutStepDO getFTPPutStepDO(JobMeta jobMeta);

    FTPDownLoadStepDO getFTPDownLoadStepDO(JobMeta jobMeta);

    JobEntryTrans getJobEntryTrans(JobMeta jobMeta);

    boolean saveIncrJobByDB(String jobName);

    boolean saveFtpIncrJobByDB(String jobName);
}
