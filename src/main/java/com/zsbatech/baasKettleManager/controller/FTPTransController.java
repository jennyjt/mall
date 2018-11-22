package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018-09-11
 */
@Api(protocols = "http,https", tags = {"Agent"}, value = "/deploy/agents", description = "ftp下载文件接口")
@RestController
@RequestMapping(value = "/deploy/agents")
public class FTPTransController {

    private static Logger logger = LoggerFactory.getLogger(FTPTransController.class);

    @ApiOperation(value = "ftp下载文件", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "下载文件成功"),})
//    @ApiImplicitParams(
//            value = {
//                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
//                    @ApiImplicitParam(paramType = "query", name = RequestField.DEPLOY_BLOCK_ID, dataType = "Long", required = true, value = "区块链部署主表ID"),
//            }
//    )
    @RequestMapping(value = "/getOperationLogList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<Boolean> getOperationLogList(HttpServletRequest request,
                                                     @RequestParam(name = RequestField.DEPLOY_BLOCK_ID, required = true) Long deployBlockId,
                                                     @RequestParam(defaultValue = "1") Integer currPage,
                                                     @RequestParam(defaultValue = "10") Integer pageSize) {

//        JobMeta jobMeta = new JobMeta();
//        jobMeta.setName("作业名称");
//        jobMeta.setName("删除文件");
//        JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
//        jobEntrySpecial.setRepeat(true);
//        jobEntrySpecial.setSchedulerType(1);
//        jobEntrySpecial.setWeekDay(1);
//        jobEntrySpecial.setDayOfMonth(1);
//        JobEntryCopy specialCopy = new JobEntryCopy(jobEntrySpecial);
//        jobMeta.addJobEntry(specialCopy);
//        JobEntryDeleteFile jobEntryDeleteFile = new JobEntryDeleteFile();
//        jobEntryDeleteFile.setName("作业名称项");
//        jobEntryDeleteFile.setFilename("要删除的删除的文件");
//        JobEntryCopy deleteFileCopy = new JobEntryCopy(jobEntryDeleteFile);
//        jobMeta.addJobEntry(deleteFileCopy);
//        jobMeta.addJobHop(new JobHopMetaVO(specialCopy, deleteFileCopy));
//        ResponseData<Boolean> responseData = new ResponseData<>();
//        responseData.setResult(saveTransMetaService.save(jobMeta, "C:\\Users\\zhang\\Desktop", true));
//        return responseData;
        return null;
    }

}

