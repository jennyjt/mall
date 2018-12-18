package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.*;
import com.zsbatech.baasKettleManager.model.*;
import com.zsbatech.baasKettleManager.service.SaveTransMetaService;
import com.zsbatech.baasKettleManager.util.ConfigUtil;
import com.zsbatech.baasKettleManager.util.StringUtil;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.EngineMetaInterface;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleMissingPluginsException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.core.util.Utils;
import org.pentaho.di.core.vfs.KettleVFS;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.di.trans.steps.insertupdate.InsertUpdateMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.pentaho.di.trans.steps.tableoutput.TableOutputMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.DataOutputStream;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018-11-15
 */
@Service
public class SaveTransMetaServiceImpl implements SaveTransMetaService {

    private String DBTransUrl = ConfigUtil.getPropertyValue("file.transMetaUrl");

    @Autowired
    private DbManagementMapper dbManagementMapper;

    @Autowired
    private InsertUpdateStepDOMapper insertUpdateStepDOMapper;

    @Autowired
    private TableOutputMetaDOMapper tableOutputMetaDOMapper;

    @Autowired
    private TransHopMetaDOMapper transHopMetaDOMapper;

    @Autowired
    private TableInputStepDOMapper tableInputStepDOMapper;

    @Autowired
    private TransMetaDOMapper transMetaDOMapper;

    public boolean save(EngineMetaInterface meta, String fname, boolean export) {
        EngineMetaInterface lmeta;
        if (export) {
            lmeta = (TransMeta) ((TransMeta) meta).realClone(false);
        } else {
            lmeta = meta;
        }
        boolean saveStatus = saveMeta(lmeta, fname);

        if (saveStatus) {
            return true;
        }

        return saveStatus;
    }

    public boolean saveMeta(EngineMetaInterface meta, String filename) {
        meta.setFilename(filename);
        if (Utils.isEmpty(meta.getName())) {
            meta.nameFromFilename();
        }
        boolean saved = false;
        try {
            String xml = XMLHandler.getXMLHeader() + meta.getXML();

            DataOutputStream dos = new DataOutputStream(KettleVFS.getOutputStream(filename, false));
            dos.write(xml.getBytes(Const.XML_ENCODING));
            dos.close();
            saved = true;
            meta.setFilename(filename);
            meta.clearChanged();
        } catch (Exception e) {
            e.printStackTrace();
            saved = false;
        }
        return saved;
    }

    @Transactional
    public boolean saveTransData(String fileName, int srcDbConnId, int dstDbConnId) {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
        TransMeta transMeta = null;
        try {
            transMeta = new TransMeta(fileName);
        } catch (KettleXMLException e) {
            e.printStackTrace();
        } catch (KettleMissingPluginsException e) {
            e.printStackTrace();
        }
        List<TransHopMeta> transHopMetas = null;
        List<TransHopMetaDO> transHopMetaDOList = new ArrayList<>();
        if (transMetaDOMapper.selectTransMetaVO(transMeta.getName()) != null) {
            return true;
        } else if (transMetaDOMapper.insert(getTransMetaDO(transMeta)) > 0) {
            TransMetaDO transMetaDO = transMetaDOMapper.selectTransMetaVO(transMeta.getName());
            InsertUpdateStepDO insertUpdateStepDO = getInsertUpdateStepDO(transMeta);
            TableOutputMetaDO tableOutputMetaDO = null;
            if (insertUpdateStepDO.getStepName() != null) {
                insertUpdateStepDO.setTransMetaId(transMetaDO.getId());
                insertUpdateStepDO.setdBManageMentId(dstDbConnId);
            } else {
                tableOutputMetaDO = getTableOutputMetaDO(transMeta);
                tableOutputMetaDO.setTransMetaId(transMetaDO.getId());
                tableOutputMetaDO.setdBManageMentId(dstDbConnId);
            }
            List<TableInputStepDO> tableInputStepDOList = getTableInputStepDO(transMeta);
            List<String> stepNameList = new ArrayList<>();
            for (TableInputStepDO tableInputStepDO : tableInputStepDOList) {
                tableInputStepDO.setTransMetaId(transMetaDO.getId());
                tableInputStepDO.setdBManageMentId(srcDbConnId);
                stepNameList.add(tableInputStepDO.getStepName());
            }
            TableOutputMetaDO tableOutputMeta = null;
            InsertUpdateStepDO insertUpdateMeta = null;
            if (insertUpdateStepDO.getStepName() != null && insertUpdateStepDOMapper.insert(insertUpdateStepDO) > 0) {
                insertUpdateMeta = insertUpdateStepDOMapper.selectByName(insertUpdateStepDO.getStepName());
            } else if (tableOutputMetaDOMapper.insert(tableOutputMetaDO) > 0) {
                tableOutputMeta = tableOutputMetaDOMapper.selectTableOutputMetaVO(tableOutputMetaDO.getStepName());
            }
            if (tableInputStepDOMapper.insertBatch(tableInputStepDOList) > 0) {
                List<TableInputStepDO> tableInputStepDOS = tableInputStepDOMapper.selectTableInputStepVO(stepNameList);
                transHopMetas = getTransHopMetas(transMeta);
                Map<String, TableInputStepDO> map = new HashMap<>();
                for (TableInputStepDO tableInputStepDO : tableInputStepDOS) {
                    map.put(tableInputStepDO.getStepName(), tableInputStepDO);
                }
                for (TransHopMeta hop : transHopMetas) {
                    TransHopMetaDO transHopMetaDO = new TransHopMetaDO();
                    transHopMetaDO.setCreateTime(new Date());
                    transHopMetaDO.setCreateTime(new Date());
                    if (hop != null && map.get(hop.getFromStep().getName()) != null) {
                        transHopMetaDO.setFromStepId(map.get(hop.getFromStep().getName()).getId());
                    }
                    if(insertUpdateMeta != null && hop != null && hop.getToStep().getName().equals(insertUpdateMeta.getStepName())){
                        transHopMetaDO.setToStepId(insertUpdateMeta.getId());
                    }else if (insertUpdateMeta == null && hop != null && hop.getToStep().getName().equals(tableOutputMeta.getStepName())) {
                        transHopMetaDO.setToStepId(tableOutputMeta.getId());
                    }
                    transHopMetaDOList.add(transHopMetaDO);
                }
            }
        }
        if(transHopMetaDOList != null && transHopMetaDOMapper.insertHopBatch(transHopMetaDOList)> 0) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取转换信息
     *
     * @param transMeta
     * @return
     */
    public TransMetaDO getTransMetaDO(TransMeta transMeta) {
        TransMetaDO transMetaDO = new TransMetaDO();
        transMetaDO.setTransName(transMeta.getName());
        transMetaDO.setFileName(transMeta.getName() + ".ktr");
        transMetaDO.setCreateTime(new Date());
        transMetaDO.setUpdateTime(new Date());
        return transMetaDO;
    }

    /**
     * 获取表输入信息
     *
     * @param transMeta
     * @return
     */
    public List<TableInputStepDO> getTableInputStepDO(TransMeta transMeta) {
        List<TableInputStepDO> tableInputStepDOList = new ArrayList<>();
        List<StepMeta> stepMeta = transMeta.getTransHopSteps(true);
        for (StepMeta tableStepMeta : stepMeta) {
            TableInputStepDO tableInputStepDO = new TableInputStepDO();
            StepMetaInterface stepMetaInterface = tableStepMeta.getStepMetaInterface();
            if (stepMetaInterface instanceof TableInputMeta) {
                tableInputStepDO.setCreateTime(new Date());
                tableInputStepDO.setUpdateTime(new Date());
                tableInputStepDO.setSql(((TableInputMeta) stepMetaInterface).getSQL());
                tableInputStepDO.setDbConnectionName(((TableInputMeta) stepMetaInterface).getDatabaseMeta().getName());
                tableInputStepDO.setStepName(tableStepMeta.getName());
                tableInputStepDOList.add(tableInputStepDO);
            }
        }
        return tableInputStepDOList;
    }

    /**
     * 获取表输出信息
     *
     * @param transMeta
     * @return
     */
    public TableOutputMetaDO getTableOutputMetaDO(TransMeta transMeta) {
        TableOutputMetaDO tableOutputMetaDO = new TableOutputMetaDO();
        List<StepMeta> stepMetaList = transMeta.getTransHopSteps(true);
        for (StepMeta stepMeta : stepMetaList) {
            StepMetaInterface stepMetaInterface = stepMeta.getStepMetaInterface();
            if (stepMetaInterface instanceof TableOutputMeta) {
                tableOutputMetaDO.setCreateTime(new Date());
                tableOutputMetaDO.setUpdateTime(new Date());
                tableOutputMetaDO.setEntryId(1);
                tableOutputMetaDO.setDbConnectionName(((TableOutputMeta) stepMetaInterface).getDatabaseMeta().getName());
                tableOutputMetaDO.setStepName(stepMeta.getName());
                tableOutputMetaDO.setIsBatchInsert(1);
                tableOutputMetaDO.setTargetTable(((TableOutputMeta) stepMetaInterface).getTableName());
                break;
            }
        }
        return tableOutputMetaDO;
    }

    /**
     * 获取增量同步信息
     *
     * @param transMeta
     * @return
     */
    public InsertUpdateStepDO getInsertUpdateStepDO(TransMeta transMeta) {
        InsertUpdateStepDO insertUpdateStepDO = new InsertUpdateStepDO();
        List<StepMeta> stepMetaList = transMeta.getTransHopSteps(true);
        for (StepMeta stepMeta : stepMetaList) {
            StepMetaInterface stepMetaInterface = stepMeta.getStepMetaInterface();
            if (stepMetaInterface instanceof InsertUpdateMeta) {
                insertUpdateStepDO.setStepName(((InsertUpdateMeta) stepMetaInterface).getDatabaseMeta().getName());
                insertUpdateStepDO.setKeyLookup(StringUtil.toString(((InsertUpdateMeta) stepMetaInterface).getKeyLookup()));
                insertUpdateStepDO.setKeyStream(StringUtil.toString((((InsertUpdateMeta) stepMetaInterface).getKeyStream())));
                insertUpdateStepDO.setKeyStream2(StringUtil.toString((((InsertUpdateMeta) stepMetaInterface).getKeyStream2())));//一定要加上
                insertUpdateStepDO.setKeyCondition(StringUtil.toString((((InsertUpdateMeta) stepMetaInterface).getKeyCondition())));
                //设置要更新的字段
                insertUpdateStepDO.setUpdateLookup(StringUtil.toString(((InsertUpdateMeta) stepMetaInterface).getUpdateLookup()));
                insertUpdateStepDO.setUpdateStream(StringUtil.toString(((InsertUpdateMeta) stepMetaInterface).getUpdateStream()));
                insertUpdateStepDO.setUpdateOrNot(StringUtil.toString(((InsertUpdateMeta) stepMetaInterface).getUpdate()));
                insertUpdateStepDO.setCreateTime(new Date());
                insertUpdateStepDO.setUpdateTime(new Date());
                insertUpdateStepDO.setTimeStampColumn(new Date());
                insertUpdateStepDO.setTargetTable(((InsertUpdateMeta) stepMetaInterface).getTableName());
                if (((InsertUpdateMeta) stepMetaInterface).isUpdateBypassed()) {
                    insertUpdateStepDO.setIsUpdate((short) 1);
                } else {
                    insertUpdateStepDO.setIsUpdate((short) 0);
                }
                break;
            }
        }
        return insertUpdateStepDO;
    }

    /**
     * 获取跳(hop)信息
     *
     * @param transMeta
     * @return
     */
    public List<TransHopMeta> getTransHopMetas(TransMeta transMeta) {
        List<TransHopMeta> transHopMetas = new ArrayList<>();
        for (int i = 0; i < transMeta.nrTransHops(); ++i) {
            TransHopMeta hop = transMeta.getTransHop(i);
            transHopMetas.add(hop);
        }
        return transHopMetas;
    }

    /**
     * 还原转换的.ktr文件
     *
     * @param fields 字段数组
     * @param name   文件名
     * @return
     */
    public boolean saveByDB(String name, String[] fields) {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
        TransMetaDO transMetaDO = transMetaDOMapper.selectTransMetaVO(name);
        TableOutputMetaDO tableOutputMetaDO = tableOutputMetaDOMapper.selectTableOutputMetaVOByTransMetaId(transMetaDO.getId());
        InsertUpdateStepDO insertUpdateStepDO = insertUpdateStepDOMapper.selectByTransMetaId(transMetaDO.getId());
        List<TableInputStepDO> tableInputStepDOList = tableInputStepDOMapper.selectTableInputStepVOS(transMetaDO.getId());
        TransMeta transMeta = new TransMeta();
        if(insertUpdateStepDO == null) {
            DbManagement outputDbManagement = dbManagementMapper.selectByPrimaryKey(tableOutputMetaDO.getdBManageMentId());
            DatabaseMeta outputDatabaseMeta = new DatabaseMeta();
            outputDatabaseMeta.setDatabaseType(outputDbManagement.getDbType());
            outputDatabaseMeta.setDBName(outputDbManagement.getDbName());
            outputDatabaseMeta.setHostname(outputDbManagement.getDbHost());
            outputDatabaseMeta.setDBPort(outputDbManagement.getDbPort());
            outputDatabaseMeta.setUsername(outputDbManagement.getDbUser());
            outputDatabaseMeta.setPassword(outputDbManagement.getDbPassword());
            outputDatabaseMeta.setName(outputDbManagement.getLinkName());
            transMeta.setName(transMetaDO.getTransName());
            transMeta.addDatabase(outputDatabaseMeta);
            TableOutputMeta tableOutputMeta = new TableOutputMeta();
            tableOutputMeta.setDatabaseMeta(transMeta.findDatabase(outputDbManagement.getLinkName()));
            tableOutputMeta.setTableName(tableOutputMetaDO.getTargetTable());
            String[] fieldsStream = fields;
            tableOutputMeta.setTableNameInTable(true);
            tableOutputMeta.setFieldDatabase(fields);
            tableOutputMeta.setFieldStream(fieldsStream);
            PluginRegistry registry = PluginRegistry.getInstance();
            if (tableOutputMetaDO.getIsBatchInsert() == 1) {
                tableOutputMeta.setUseBatchUpdate(true);
            } else {
                tableOutputMeta.setUseBatchUpdate(false);
            }
            String tableOutputPluginId = registry.getPluginId(StepPluginType.class, tableOutputMeta);
            StepMeta tableOutputStepMeta = new StepMeta(tableOutputPluginId, tableOutputMetaDO.getStepName(), tableOutputMeta);
            transMeta.addStep(tableOutputStepMeta);
            for (TableInputStepDO tableInputStepDO : tableInputStepDOList) {
                DbManagement inputDbManagement = dbManagementMapper.selectByPrimaryKey(tableInputStepDO.getdBManageMentId());
                DatabaseMeta inputDatabaseMeta = new DatabaseMeta();
                inputDatabaseMeta.setDatabaseType(inputDbManagement.getDbType());
                inputDatabaseMeta.setDBName(inputDbManagement.getDbName());
                inputDatabaseMeta.setHostname(inputDbManagement.getDbHost());
                inputDatabaseMeta.setDBPort(inputDbManagement.getDbPort());
                inputDatabaseMeta.setUsername(inputDbManagement.getDbUser());
                inputDatabaseMeta.setPassword(inputDbManagement.getDbPassword());
                inputDatabaseMeta.setName(inputDbManagement.getLinkName());
                transMeta.addDatabase(inputDatabaseMeta);
                TableInputMeta tableInputMeta = new TableInputMeta();
                tableInputMeta.setDatabaseMeta(transMeta.findDatabase(tableInputStepDO.getDbConnectionName()));
                tableInputMeta.setSQL(tableInputStepDO.getExcSql());
                String tableInputPluginId = registry.getPluginId(StepPluginType.class, tableInputMeta);
                StepMeta tableInputStepMeta = new StepMeta(tableInputPluginId, tableInputStepDO.getStepName(), tableInputMeta);
                transMeta.addStep(tableInputStepMeta);
                transMeta.addTransHop(new TransHopMeta(tableInputStepMeta, tableOutputStepMeta));
            }
        }else {
            DbManagement insertDbManagement = dbManagementMapper.selectByPrimaryKey(insertUpdateStepDO.getdBManageMentId());
            DatabaseMeta insertDatabaseMeta = new DatabaseMeta();
            insertDatabaseMeta.setDatabaseType(insertDbManagement.getDbType());
            insertDatabaseMeta.setDBName(insertDbManagement.getDbName());
            insertDatabaseMeta.setHostname(insertDbManagement.getDbHost());
            insertDatabaseMeta.setDBPort(insertDbManagement.getDbPort());
            insertDatabaseMeta.setUsername(insertDbManagement.getDbUser());
            insertDatabaseMeta.setPassword(insertDbManagement.getDbPassword());
            insertDatabaseMeta.setName(insertDbManagement.getLinkName());
            transMeta.setName(transMetaDO.getTransName());
            transMeta.addDatabase(insertDatabaseMeta);
            InsertUpdateMeta insertUpdateMeta = new InsertUpdateMeta();
            insertUpdateMeta.setDatabaseMeta(transMeta.findDatabase(insertDbManagement.getLinkName()));
            insertUpdateMeta.setDatabaseMeta(insertDatabaseMeta);
            insertUpdateMeta.setTableName(insertUpdateStepDO.getTargetTable());
            insertUpdateMeta.setKeyLookup(insertUpdateStepDO.getKeyLookup().split(","));
            insertUpdateMeta.setKeyStream(insertUpdateStepDO.getKeyStream().split(","));
            if(insertUpdateStepDO.getKeyStream2() != null) {
                insertUpdateMeta.setKeyStream2(insertUpdateStepDO.getKeyStream2().split(","));//一定要加上
            }else {
                insertUpdateMeta.setKeyStream2(new String[]{""});
            }
            insertUpdateMeta.setKeyCondition(insertUpdateStepDO.getKeyCondition().split(","));
            insertUpdateMeta.setUpdateStream(insertUpdateStepDO.getUpdateStream().split(","));
            String [] strings = insertUpdateStepDO.getUpdateOrNot().split(",");
            Boolean [] booleans = new Boolean[strings.length];
            for(int i = 0; i < strings.length ; i++){
                booleans[i] = Boolean.valueOf(strings[i]);
            }
            insertUpdateMeta.setUpdate(booleans);
            insertUpdateMeta.setUpdateLookup(insertUpdateStepDO.getUpdateLookup().split(","));
            if(insertUpdateStepDO.getIsUpdate() == 1) {
                insertUpdateMeta.setUpdateBypassed(true);
            }else {
                insertUpdateMeta.setUpdateBypassed(false);
            }
            PluginRegistry registry = PluginRegistry.getInstance();
            insertUpdateMeta.setDatabaseMeta(transMeta.findDatabase(insertDatabaseMeta.getName()));
            String insertUpdatePluginId = registry.getPluginId(StepPluginType.class, insertUpdateMeta);
            StepMeta insertUpdateStepMeta = new StepMeta(insertUpdatePluginId, insertUpdateStepDO.getStepName(), insertUpdateMeta);
            transMeta.addStep(insertUpdateStepMeta);
            for (TableInputStepDO tableInputStepDO : tableInputStepDOList) {
                DbManagement inputDbManagement = dbManagementMapper.selectByPrimaryKey(tableInputStepDO.getdBManageMentId());
                DatabaseMeta inputDatabaseMeta = new DatabaseMeta();
                inputDatabaseMeta.setDatabaseType(inputDbManagement.getDbType());
                inputDatabaseMeta.setDBName(inputDbManagement.getDbName());
                inputDatabaseMeta.setHostname(inputDbManagement.getDbHost());
                inputDatabaseMeta.setDBPort(inputDbManagement.getDbPort());
                inputDatabaseMeta.setUsername(inputDbManagement.getDbUser());
                inputDatabaseMeta.setPassword(inputDbManagement.getDbPassword());
                inputDatabaseMeta.setName(inputDbManagement.getLinkName());
                transMeta.addDatabase(inputDatabaseMeta);
                TableInputMeta tableInputMeta = new TableInputMeta();
                tableInputMeta.setSQL(tableInputStepDO.getExcSql());
                tableInputMeta.setDatabaseMeta(transMeta.findDatabase(tableInputStepDO.getDbConnectionName()));
                String tableInputPluginId = registry.getPluginId(StepPluginType.class, tableInputMeta);
                StepMeta tableInputStepMeta = new StepMeta(tableInputPluginId, tableInputStepDO.getStepName(), tableInputMeta);
                transMeta.addStep(tableInputStepMeta);
                transMeta.addTransHop(new TransHopMeta(tableInputStepMeta, insertUpdateStepMeta));
            }
        }
        return save(transMeta, DBTransUrl + name + ".ktr", true);
    }
}
