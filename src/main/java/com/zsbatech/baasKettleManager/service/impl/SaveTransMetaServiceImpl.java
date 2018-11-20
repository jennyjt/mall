package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.TableOutputMetaVOMapper;
import com.zsbatech.baasKettleManager.dao.TableInputStepVOMapper;
import com.zsbatech.baasKettleManager.dao.TransHopMetaVOMapper;
import com.zsbatech.baasKettleManager.dao.TransMetaVOMapper;
import com.zsbatech.baasKettleManager.service.SaveTransMetaService;
import com.zsbatech.baasKettleManager.vo.TableInputStepVO;
import com.zsbatech.baasKettleManager.vo.TableOutputMetaVO;
import com.zsbatech.baasKettleManager.vo.TransHopMetaVO;
import com.zsbatech.baasKettleManager.vo.TransMetaVO;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.EngineMetaInterface;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.RowMetaAndData;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleMissingPluginsException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.core.row.RowMeta;
import org.pentaho.di.core.util.Utils;
import org.pentaho.di.core.vfs.KettleVFS;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.di.trans.steps.fieldsplitter.FieldSplitterData;
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

    @Autowired
    private TableOutputMetaVOMapper tableOutputMetaVOMapper;

    @Autowired
    private TransHopMetaVOMapper transHopMetaVOMapper;

    @Autowired
    private TableInputStepVOMapper tableInputStepVOMapper;

    @Autowired
    private TransMetaVOMapper transMetaVOMapper;

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
        }
        return saved;
    }

    @Transactional
    public boolean saveTransData(String fileName) {
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
        List<TransHopMetaVO> transHopMetaVOList = new ArrayList<>();
        if (transMetaVOMapper.selectTransMetaVO(transMeta.getName()) != null) {
            return true;
        } else if (transMetaVOMapper.insert(getTransMetaVO(transMeta)) > 0) {
            TransMetaVO transMetaVO = transMetaVOMapper.selectTransMetaVO(transMeta.getName());
            TableOutputMetaVO tableOutputMetaVO = getTableOutputMetaVO(transMeta);
            tableOutputMetaVO.setTransMetaId(transMetaVO.getId());
            List<TableInputStepVO> tableInputStepVOList = getTableInputStepVO(transMeta);
            List<String> stepNameList = new ArrayList<>();
            for (TableInputStepVO tableInputStepVO : tableInputStepVOList) {
                tableInputStepVO.setTransMetaId(transMetaVO.getId());
                stepNameList.add(tableInputStepVO.getStepName());
            }
            int toStepId = 0;
            TableOutputMetaVO tableOutputMeta = null;
            if (tableOutputMetaVOMapper.insert(tableOutputMetaVO) > 0) {
                tableOutputMeta = tableOutputMetaVOMapper.selectTableOutputMetaVO(tableOutputMetaVO.getStepName());

            }
            if (tableInputStepVOMapper.insert(tableInputStepVOList) > 0) {
                List<TableInputStepVO> tableInputStepVOS = tableInputStepVOMapper.selectTableInputStepVO(stepNameList);
                transHopMetas = getTransHopMetas(transMeta);
                Map<String, TableInputStepVO> map = new HashMap<>();
                for (TableInputStepVO tableInputStepVO : tableInputStepVOS) {
                    map.put(tableInputStepVO.getStepName(), tableInputStepVO);
                }
                for (TransHopMeta hop : transHopMetas) {
                    TransHopMetaVO transHopMetaVO = new TransHopMetaVO();
                    transHopMetaVO.setCreateTime(new Date());
                    transHopMetaVO.setCreateTime(new Date());
                    if (hop != null && map.get(hop.getFromStep().getName()) != null) {
                        transHopMetaVO.setFromStepId(map.get(hop.getFromStep().getName()).getId());
                    }
                    if ((hop != null && hop.getToStep().getName().equals(tableOutputMeta.getStepName()))) {
                        transHopMetaVO.setToStepId(tableOutputMeta.getId());
                    }
                    transHopMetaVOList.add(transHopMetaVO);
                }
            }
            if (transHopMetaVOMapper.insertHopBatch(transHopMetaVOList) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取转换信息
     *
     * @param transMeta
     * @return
     */
    public TransMetaVO getTransMetaVO(TransMeta transMeta) {
        TransMetaVO transMetaVO = new TransMetaVO();
        transMetaVO.setName(transMeta.getName());
        transMetaVO.setFileName(transMeta.getName() + ".ktr");
        transMetaVO.setCreateTime(new Date());
        transMetaVO.setUpdateTime(new Date());
        return transMetaVO;
    }

    /**
     * 获取表输入信息
     *
     * @param transMeta
     * @return
     */
    public List<TableInputStepVO> getTableInputStepVO(TransMeta transMeta) {
        List<TableInputStepVO> tableInputStepVOList = new ArrayList<>();
        List<StepMeta> stepMeta = transMeta.getTransHopSteps(true);
        for (StepMeta tableStepMeta : stepMeta) {
            TableInputStepVO tableInputStepVO = new TableInputStepVO();
            StepMetaInterface stepMetaInterface = tableStepMeta.getStepMetaInterface();
            if (stepMetaInterface instanceof TableInputMeta) {
                tableInputStepVO.setCreateTime(new Date());
                tableInputStepVO.setUpdateTime(new Date());
                tableInputStepVO.setSql(((TableInputMeta) stepMetaInterface).getSQL());
                tableInputStepVO.setDbConnectionName(((TableInputMeta) stepMetaInterface).getDatabaseMeta().getName());
                tableInputStepVO.setStepName(tableStepMeta.getName());
                tableInputStepVOList.add(tableInputStepVO);
            }
        }
        return tableInputStepVOList;
    }

    /**
     * 获取表输出信息
     *
     * @param transMeta
     * @return
     */
    public TableOutputMetaVO getTableOutputMetaVO(TransMeta transMeta) {
        TableOutputMetaVO tableOutputMetaVO = new TableOutputMetaVO();
        List<StepMeta> stepMetaList = transMeta.getTransHopSteps(true);
        for (StepMeta stepMeta : stepMetaList) {
            StepMetaInterface stepMetaInterface = stepMeta.getStepMetaInterface();
            if (stepMetaInterface instanceof TableOutputMeta) {
                tableOutputMetaVO.setCreateTime(new Date());
                tableOutputMetaVO.setUpdateTime(new Date());
                tableOutputMetaVO.setEntryId(1);
                tableOutputMetaVO.setDbConnectionName(((TableOutputMeta) stepMetaInterface).getDatabaseMeta().getName());
                tableOutputMetaVO.setStepName(stepMeta.getName());
                tableOutputMetaVO.setIsBatchInsert(1);
                tableOutputMetaVO.setTargetTable(((TableOutputMeta) stepMetaInterface).getTableName());
                break;
            }
        }
        return tableOutputMetaVO;
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
     * @param fields 字段数组
     * @param name 文件名
     * @return
     */
    public boolean saveByDB(String name , String[] fields) {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
        TransMetaVO transMetaVO = transMetaVOMapper.selectTransMetaVO(name);
        TableOutputMetaVO tableOutputMetaVO = tableOutputMetaVOMapper.selectTableOutputMetaVOByTransMetaId(transMetaVO.getId());
        List<TableInputStepVO> tableInputStepVOList = tableInputStepVOMapper.selectTableInputStepVOS(transMetaVO.getId());
        TransMeta transMeta = new TransMeta();
        DatabaseMeta databaseMeta = new DatabaseMeta();
        databaseMeta.setDatabaseType("MySQL");
        databaseMeta.setDBName("kettle");
        databaseMeta.setHostname("localhost");
        databaseMeta.setDBPort("3306");
        databaseMeta.setUsername("root");
        databaseMeta.setPassword("root");
        databaseMeta.setName("sample");
        transMeta.addDatabase(databaseMeta);
        transMeta.setName(transMetaVO.getName());
        TableOutputMeta tableOutputMeta = new TableOutputMeta();
        tableOutputMeta.setTableName(tableOutputMetaVO.getTargetTable());
        String[] fieldsStream = fields;
        tableOutputMeta.setTableNameInTable(true);
        tableOutputMeta.setFieldDatabase(fields);
        tableOutputMeta.setFieldStream(fieldsStream);
        PluginRegistry registry = PluginRegistry.getInstance();
        if (tableOutputMetaVO.getIsBatchInsert() == 1) {
            tableOutputMeta.setUseBatchUpdate(true);
        } else {
            tableOutputMeta.setUseBatchUpdate(false);
        }
        tableOutputMeta.setDatabaseMeta(transMeta.findDatabase("sample"));
        String tableOutputPluginId = registry.getPluginId(StepPluginType.class, tableOutputMeta);
        StepMeta tableOutputStepMeta = new StepMeta(tableOutputPluginId, tableOutputMetaVO.getStepName(), tableOutputMeta);
        transMeta.addStep(tableOutputStepMeta);
        for (TableInputStepVO tableInputStepVO : tableInputStepVOList) {
            TableInputMeta tableInputMeta = new TableInputMeta();
            tableInputMeta.setSQL(tableInputStepVO.getExcSql());
            tableInputMeta.setDatabaseMeta(transMeta.findDatabase("sample"));
            String tableInputPluginId = registry.getPluginId(StepPluginType.class, tableInputMeta);
            StepMeta tableInputStepMeta = new StepMeta(tableInputPluginId, tableInputStepVO.getStepName(), tableInputMeta);
            transMeta.addStep(tableInputStepMeta);
            transMeta.addTransHop(new TransHopMeta(tableInputStepMeta,tableOutputStepMeta));
        }
        save(transMeta, "C:\\Users\\zhang\\Desktop\\jdee.ktr", true);
        return true;
    }

}
