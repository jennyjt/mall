package com.zsbatech.baasKettleManager.util;

import java.util.HashMap;

public class CommonConstans {

    //提示信息
    public static final class  MESSAGE{
        public static final String CAN_NOT_FOUNT_BLOCK = "未查询到区块链信息！";
        public static final String CAN_NOT_FOUNT_DEPLOY_INFO = "未查询到区块链部署信息！";
        public static final String DEPLOY_INFO_ALREADY_DELETE= "该区块链部署信息已删除！";
        public static final String CAN_NOT_FOUNT_IMAGE_CONFIG = "未查询到镜像信息！";
        public static final String AUTH_CONFIG_USER_FAIL = "配置人员与部署人员不符！";
        public static final String CAN_NOT_FOUNT_WAIT_DEPLOY = "未查询待部署到节点信息！";
        public static final String CAN_NOT_FOUNT_WAIT_STOP = "未查询到待停用节点信息！";
        public static final String CAN_NOT_FOUNT_ADD_ITEM = "未获取到新增节点信息！";
        public static final String CAN_NOT_FOUNT_WAIT_DELETE = "未查询到待删除节点信息！";
        public static final String CAN_NOT_FOUNT_WAIT_START = "未查询到待启用的节点信息！";
        public static final String CAN_NOT_FOUNT_WAIT_RESET = "未查询到待重置的节点信息！";
        public static final String CAN_NOT_FOUNT_ITEM = "未查询到节点信息！";
        public static final String CAN_NOT_DELETE_ITEM = "该状态的节点不可删除！";
        public static final String CAN_NOT_STOP_ITEM = "该状态的节点不可停用！";
        public static final String CAN_NOT_START_ITEM = "该状态的节点不可启动！";
        public static final String CAN_NOT_RESET_ITEM = "该状态的节点不可重置！";
        public static final String CAN_NOT_DEPLOY_ITEM = "该状态的节点不可部署！";

        public static final String RECEIVE_MESSAGE_ERROR = "参数异常！";
        public static final String SWITCH_DATABASE_ERROR = "未获取到数据容器地址！";
        public static final String SWITCH_NO_ITEM_ERROR = "未获取到Agent连接的节点！";

        public static final String CONTRACT_INSTALL_ERROR = "合约安装失败！";
        public static final String SWITCH_MYSQL_DEPLOY_ERROR="switch[mysql]部署失败！";
        public static final String SWITCH_DEPLOY_ERROR="switch部署失败！";

        public static final String CAN_NOT_DELETE_BROWSER = "区块链浏览器所在的节点不可删除！";
        public static final String RESOURCE_NULL_ERROR = "资源ID不能为空！";

        public static final String CAN_NOT_DELETE_BECAUSE_DAPP = "已经部署DAPP资源，请先删除DAPP资源！";
        public static final String CAN_NOT_STOP_BECAUSE_DAPP = "已经部署DAPP资源，请先停用DAPP资源！";
        public static final String CAN_NOT_STOP_ITEM_BECAUSE_DAPP = "存在访问当前节点的DAPP资源，请先停用DAPP资源！";
    }
    //deploy_block_info表的deploy_status码表
    public static final class  DEPLOY_STATUS{
        //0新增 1部署 2停用 3删除
        public static final int ADD = 0;
        public static final int DEPLOY = 1;
        public static final int STOP = 2;
        public static final int DELETE = 3;
    }
    //deploy_block_item表的item_status码表
    public static final class  ITEM_STATUS{
        //0:待部署,1:部署中,2:运行中,3:停止,4:删除
        public static final int WAIT_DEPLOY = 0;
        public static final int DEPLOY = 1;
        public static final int RUNNING = 2;
        public static final int STOP = 3;
        public static final int DELETE = 4;
    }
    //dapp_resource_info表的resource_status码表
    public static final class  DAPP_INFO_STATUS{
        //状态(0待部署，1已部署，2已停用，3已删除)
        public static final int WAIT_DEPLOY = 0;
        public static final int DEPLOY = 1;
        public static final int STOP = 2;
        public static final int DELETE = 3;
    }
    public static final class  OPERATE_RESULT{
        //0:失败,1成功
        public static final int FAIL_0 = 0;
        public static final int SUCC_1 = 1;
        public static final String FAILSTR_0 = "0";
        public static final String SUCCSTR_1 = "1";
    }
    //deploy_block_operation_log表中的operate_type
    public static final class  OPERATE_TYPE{
        //0新增、1启动、2停止、3重置、4删除
        public static final int ADD = 0;
        public static final int START = 1;
        public static final int STOP = 2;
        public static final int RESET = 3;
        public static final int DELETE = 4;
    }
    //dapp_file_config表中的file_status
    public static final class FILE_STATUS{
        //0不可用、1可用
        public static final int UNAVAILABLE = 0;
        public static final int AVAILABLE = 1;
    }

    public static final HashMap<Integer,String> ConsensusTypeMap = new HashMap();
    public static final HashMap<Integer,String> BlockTypeMap = new HashMap();
    public static final HashMap<Integer,String> DeployStatusMap = new HashMap();
    static {
        //0:dpos,1:pow,2:pbft
        ConsensusTypeMap.put(0,"dpos");
        ConsensusTypeMap.put(1,"pow");
        ConsensusTypeMap.put(2,"pbft");
        //0:私有链,1:联盟链
        BlockTypeMap.put(0,"私有链");
        BlockTypeMap.put(1,"联盟链");

        //0新增 1部署 2停用 3删除
        DeployStatusMap.put(0,"新增");
        DeployStatusMap.put(1,"部署");
        DeployStatusMap.put(2,"停用");
        DeployStatusMap.put(3,"删除");
    }

}
