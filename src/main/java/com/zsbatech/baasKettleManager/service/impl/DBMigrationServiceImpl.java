package com.zsbatech.baasKettleManager.service.impl;


import com.zsbatech.baasKettleManager.dao.*;
import com.zsbatech.baasKettleManager.model.*;
import com.zsbatech.baasKettleManager.service.DBMigrationService;
import com.zsbatech.base.common.ResponseData;
import org.pentaho.di.core.KettleEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


/**
 * @author Caohuaijie@zsbatech.com
 * @date 2018/11/19 15:06
 */

@Service
public class DBMigrationServiceImpl implements DBMigrationService {

    @Autowired
    private DataMigrationMapper dataMigrationMapper;
    @Autowired
    private TransMetaMapper transMetaMapper;
    @Autowired
    private TransHopMapper transHopMapper;
    @Autowired
    private SrcDbConnectionMapper srcDbConnectionMapper;
    @Autowired
    private DstDbConnectionMapper dstDbConnectionMapper;
    @Autowired
    private TableinputStepMapper tableinputStepMapper;
    @Autowired
    TableoutputStepMapper tableoutputStepMapper;


    public ResponseData<String> createMigration(DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();
        try {
            Date date = new Date();
            System.out.println(date);
            DataMigration dataMigration = new DataMigration();

            TransMeta transMeta = new TransMeta();
            TransHop transHop = new TransHop();
            SrcDbConnection srcDbConnection = new SrcDbConnection();
            DstDbConnection dstDbConnection = new DstDbConnection();
            TableinputStep tableinputStep = new TableinputStep();
            TableoutputStep tableoutputStep = new TableoutputStep();

            //trans_meta
            transMeta.setTransName(dataMig.getTransName());
            transMeta.setFileName(dataMig.getFileName());
            transMeta.setCreatetime(date);
            transMeta.setUpdatetime(date);
            transMetaMapper.insert(transMeta);
            int transMetaId = transMeta.getId();
            System.out.println(transMetaId);

            //input step
            tableinputStep.setCreatetime(date);
            tableinputStep.setUpdatetime(date);
            tableinputStep.setTransStepName(dataMig.getTransStepName());
            tableinputStep.setExcSql(dataMig.getExcSql());
            tableinputStep.setTransMetaId(transMetaId);
            tableinputStepMapper.insert(tableinputStep);

            int inputStepId = tableinputStep.getId();
            System.out.println(inputStepId);

            //output step
            tableoutputStep.setCreatetime(date);
            tableoutputStep.setUpdatetime(date);
            tableoutputStep.setStepName(dataMig.getStepName());
            tableoutputStep.setDbConnectionName(dataMig.getDstLinkName());
            tableoutputStep.setTargetTable(dataMig.getTargetTable());
            tableoutputStep.setTransMetaId(transMetaId);

            tableoutputStepMapper.insert(tableoutputStep);
            int outputStepId = tableoutputStep.getId();
            System.out.println(outputStepId);

            //src_db_Conn
            srcDbConnection.setCreated(date);
            srcDbConnection.setUpdated(date);
            srcDbConnection.setLinkName(dataMig.getSrcLinkName());
            srcDbConnection.setDbHost(dataMig.getSrcDbHost());
            srcDbConnection.setDbPort(dataMig.getSrcDbPort());
            srcDbConnection.setDbName(dataMig.getSrcDbName());
            srcDbConnection.setDbUser(dataMig.getSrcDbUser());
            srcDbConnection.setDbPassword(dataMig.getSrcDbPassword());
            srcDbConnection.setSrcTable(dataMig.getSrcTable());
            srcDbConnection.setSrcColumn(dataMig.getSrcColumn());
            srcDbConnection.setSrcSql(dataMig.getSrcSql());
            srcDbConnection.setStepId(inputStepId);
            srcDbConnectionMapper.insert(srcDbConnection);
            int srcDbConnId = srcDbConnection.getId();

            //dst_db_Conn
            dstDbConnection.setCreated(date);
            dstDbConnection.setUpdated(date);
            dstDbConnection.setLinkName(dataMig.getDstLinkName());
            dstDbConnection.setDbHost(dataMig.getDstDbHost());
            dstDbConnection.setDbPort(dataMig.getDstDbPort());
            dstDbConnection.setDbName(dataMig.getDstDbName());
            dstDbConnection.setDbUser(dataMig.getDstDbUser());
            dstDbConnection.setDbPassword(dataMig.getDstDbPassword());
            dstDbConnection.setDstTable(dataMig.getDstTable());
            dstDbConnection.setDstColumn(dataMig.getDstColumn());
            dstDbConnection.setDstSql(dataMig.getDstSql());
            dstDbConnection.setStepId(outputStepId);
            dstDbConnectionMapper.insert(dstDbConnection);
            int dstDbConnId = dstDbConnection.getId();

            //trans_hop
            transHop.setCreatetime(date);
            transHop.setUpdatetime(date);
            transHop.setFromStepId(srcDbConnId);
            transHop.setToStepId(dstDbConnId);
            transHopMapper.insert(transHop);
            int transHopId = transHop.getId();

            //dataMigration

            dataMigration.setCreated(date);
            dataMigration.setUpdated(date);

            dataMigration.setHopId(transHopId);
            dataMigration.setTransMetaId(transMetaId);
            dataMigration.setSrcDbconnId(srcDbConnId);
            dataMigration.setDstDbconnId(dstDbConnId);
            dataMigration.setInputStepId(inputStepId);
            dataMigration.setOutputStepId(outputStepId);
            dataMigration.setSqlString(dataMig.getSqlString());
            dataMigrationMapper.insert(dataMigration);

            responseData.setOK(200,"success","success");
            return responseData;
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError("fail!");
        }

        return responseData;
    }

}
