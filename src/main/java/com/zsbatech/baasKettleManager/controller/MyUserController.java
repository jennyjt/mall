package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.model.MyUser;
import com.zsbatech.baasKettleManager.service.MyUserService;
import com.zsbatech.base.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
//@RequestMapping(value = "/data_center/datasource")

@RequestMapping(value = "/user")
public class MyUserController {


  @Autowired
  private MyUserService userService;


  @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ResponseData<String> addData(HttpServletRequest request,
                                      @RequestBody MyUser user) {
      ResponseData<String> responseData = new ResponseData<>();

      //校验数据源是否能够连接
      boolean result = userService.add(user);
    
      
      if(result){
          responseData.setOK("success", "添加成功！");
      }else{
          responseData.setError("fail");
      }
      System.out.println();
      return responseData;
  }


    @RequestMapping(value = "/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<List<MyUser>> findData(HttpServletRequest request,
                                                 @RequestBody MyUser user) {
        ResponseData<List<MyUser>> responseData = new ResponseData<>();

        //校验数据源是否能够连接
        List<MyUser> result = userService.find(user);
        String ss="success";


        if(result.size()>0){
            responseData.setOK(200,ss, result);
        }else{
            responseData.setError("fail");
        }
        return responseData;



    }
  
}

