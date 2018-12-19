package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.DbManagementMapper;
import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.baasKettleManager.service.DBMigrationService;
import com.zsbatech.baasKettleManager.service.JobTransService;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import com.zsbatech.baasKettleManager.service.SaveTransMetaService;
import com.zsbatech.baasKettleManager.util.ConfigUtil;
import com.zsbatech.base.common.ResponseData;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.job.JobHopMeta;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.special.JobEntrySpecial;
import org.pentaho.di.job.entries.trans.JobEntryTrans;
import org.pentaho.di.job.entry.JobEntryCopy;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.pentaho.di.trans.steps.tableoutput.TableOutputMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobTransServiceImpl implements JobTransService{

    @Autowired
    private DbManagementMapper dbManagementMapper;
    @Autowired
    SaveTransMetaService saveTransMetaService;
    @Autowired
    SaveJobMetaService saveJobMetaService;
    @Autowired
    JobTransService jobTransService;
    @Autowired
    DBMigrationService dbMigrationService;

    private String DbMigJobUrl = ConfigUtil.getPropertyValue("file.jobMetaUrl");
    private String ktrpath;


    public ResponseData<String> modifyJob(DataMig dataMig) {


        ResponseData<String> responseData = new ResponseData<>();
        try {

            ktrpath = dbMigrationService.generateKtr(dataMig);
            KettleEnvironment.init();


            JobMeta jobMeta = new JobMeta();
            jobMeta.setName(dataMig.getJobName());
            JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
            jobEntrySpecial.setName("start");
            jobEntrySpecial.setRepeat(true);


            switch (dataMig.getSchedulerType()){
                case 1:
                    jobEntrySpecial.setSchedulerType(1);
                    jobEntrySpecial.setIntervalSeconds(10);
                    jobEntrySpecial.setIntervalMinutes(0);
                    break;


                case 2:
                    jobEntrySpecial.setSchedulerType(2);
                    jobEntrySpecial.setHour(12);
                    break;
                case 3:
                    jobEntrySpecial.setSchedulerType(3);
                    jobEntrySpecial.setWeekDay(1);
                    break;
                case 4:
                    jobEntrySpecial.setSchedulerType(4);
                    jobEntrySpecial.setDayOfMonth(1);
                    break;
                default:
                    jobEntrySpecial.setSchedulerType(0);
                    jobEntrySpecial.setRepeat(false);
                    break;

            }

            jobEntrySpecial.setStart(true);
            JobEntryCopy specialCopy = new JobEntryCopy(jobEntrySpecial);
            specialCopy.setLocation(30,30);
            specialCopy.setDrawn(true);
            jobMeta.addJobEntry(specialCopy);
            JobEntryTrans jobEntryTrans = new JobEntryTrans(dataMig.getJobName());
            jobEntryTrans.setFileName(ktrpath);
            JobEntryCopy transJob = new JobEntryCopy(jobEntryTrans);
            transJob.setLocation(200,20);
            transJob.setDrawn(true);
            jobMeta.addJobEntry(transJob);

            JobHopMeta jobHopMeta = new JobHopMeta(specialCopy, transJob);
            jobHopMeta.setUnconditional(true);
            jobMeta.addJobHop(jobHopMeta);

            boolean savektr = saveJobMetaService.save(jobMeta,DbMigJobUrl+dataMig.getJobName()+".kjb",true);
            boolean savejob = saveJobMetaService.saveTransJobData(DbMigJobUrl+dataMig.getJobName()+".kjb");
            if (savektr&&savejob){
                responseData.set(200,"success",DbMigJobUrl+dataMig.getJobName()+".kjb");
            }


        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError(500,"创建任务异常，请确认输入参数是否正确",DbMigJobUrl+dataMig.getJobName()+".kjb");
        }

        return responseData;
    }

}

