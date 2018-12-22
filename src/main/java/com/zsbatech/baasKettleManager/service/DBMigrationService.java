package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.model.DbJobInfo;
import com.zsbatech.baasKettleManager.model.DbResponse;
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
    ResponseData<String> cycleMigration(DataMig dataMig);
    Pagination<DbJobInfo> getJobList(Integer currPage, Integer pageSize);
    String generateKtr(DataMig dataMig);
    DbJobInfo getJobDetail(String jobName);
    boolean checkUniqueJobName(String jobName);
}
