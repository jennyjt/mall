package com.zsbatech.baasKettleManager;

import com.zsbatech.baasKettleManager.model.FtpSourceManager;
import com.zsbatech.baasKettleManager.service.FtpSouceManageService;
import com.zsbatech.base.common.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FtpSourceManageTests {
    @Autowired
    private FtpSouceManageService ftpService;

    @Test
    public void testSaveFtpSource(){
        FtpSourceManager conn = new FtpSourceManager();
        conn.setFtpHost("127.0.0.1");
        conn.setFtpPort("22");
        conn.setUserName("root");
        conn.setPassWord("123456");
        boolean result = ftpService.createDataSource(conn);
        assertSame(true, result);
    }

    @Test
    public void testUpdateFtpSource(){
        FtpSourceManager conn = new FtpSourceManager();
        conn.setId(2);
        conn.setFtpHost("127.0.0.1");
        conn.setFtpPort("22000");
        conn.setUserName("root");
        conn.setPassWord("123456");
        boolean result = ftpService.updateDataSource(conn);
        assertSame(true, result);
    }

    @Test
    public void testGetFtpSources(){
        Pagination<FtpSourceManager> conns = ftpService.getDataSources(1, 2, null);
        assertNotNull(conns);
    }

}
