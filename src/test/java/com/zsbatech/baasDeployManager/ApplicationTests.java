package com.zsbatech.baasDeployManager;

import com.zsbatech.baasKettleManager.service.impl.SaveTransMetaServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {


    @Test
    public void save() {
        TransMeta transMeta = null;
        try {
            KettleEnvironment.init();
            transMeta = new TransMeta();
            transMeta.setName("转换名称");
        } catch (KettleException e) {
            e.printStackTrace();
        }
        new SaveTransMetaServiceImpl().save(transMeta, "C:\\Users\\zhang\\Desktop\\aaa.ktr", true);
    }

    @Test
    public void test() throws  Exception{
        KettleEnvironment.init();
        TransMeta transMeta = new TransMeta("C:\\Users\\zhang\\Desktop\\jdbc.ktr");
        Trans trans = new Trans(transMeta);
        trans.prepareExecution(null);
        trans.startThreads();
        trans.waitUntilFinished();
        if(trans.getErrors()!=0)
        {
            System.out.println("Error encountered");
        }
    }
}
