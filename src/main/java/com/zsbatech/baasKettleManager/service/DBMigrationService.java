package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.model.JobMeta;
import com.zsbatech.base.common.Pagination;
import com.zsbatech.base.common.ResponseData;
import org.springframework.stereotype.Service;

/**
 * @author Caohuaijie@zsbatech.com
 * @date 2018/11/19 14:57
 */

@Service
public interface DBMigrationService {
    ResponseData<String> createMigration(DataMig dataMig);
    ResponseData<String> cycleMigration(DataMig dataMig);
    ResponseData<String> insertupdateMigration(DataMig dataMig);
    Pagination<JobMeta> getJobList(Integer currPage, Integer pageSize);
    JobMeta getJobDetail(Integer jobId);
}
