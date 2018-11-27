package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.controller.ContentManageController;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/27
 */
@Service
public class JobExcuteServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(ContentManageController.class);

    public void excute(String fileName) throws Exception{
        KettleEnvironment.init();
        JobMeta jobMeta = new JobMeta(fileName, null);
        Job job = new Job(null, jobMeta);
        job.start();
        job.waitUntilFinished();
        if (job.getErrors() != 0) {
            logger.info("job运行异常");
        }
    }
}
