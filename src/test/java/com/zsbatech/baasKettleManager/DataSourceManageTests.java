package com.zsbatech.baasKettleManager;

import com.zsbatech.baasKettleManager.model.DstDbConnection;
import com.zsbatech.baasKettleManager.model.SrcDbConnection;
import com.zsbatech.baasKettleManager.service.DataSouceManageService;
import com.zsbatech.base.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceManageTests {
    @Autowired
    private DataSouceManageService dbService;

    @Test
    public void testSaveSrcConnection(){
        SrcDbConnection conn = new SrcDbConnection();
        conn.setJobId(11);
        conn.setStepId(1);
        conn.setLinkName("my_test");
        conn.setDbHost("127.0.0.1");
        conn.setDbPort("3306");
        conn.setDbName("my_test");
        conn.setDbUser("test");
        conn.setDbPassword("123456");
        conn.setCreated(DateUtils.currentDateTime());
        conn.setUpdated(DateUtils.currentDateTime());
        boolean result = dbService.createSrcDataSource(conn);
        assertSame(true, result);
    }

    @Test
    public void testSaveSrcConnectionList(){
        List<SrcDbConnection> connList = new ArrayList<>();
        SrcDbConnection conn = new SrcDbConnection();
        conn.setJobId(11);
        conn.setStepId(1);
        conn.setLinkName("my_test");
        conn.setDbHost("127.0.0.1");
        conn.setDbPort("3306");
        conn.setDbName("my_test");
        conn.setDbUser("test");
        conn.setDbPassword("123456");
        conn.setCreated(DateUtils.currentDateTime());
        conn.setUpdated(DateUtils.currentDateTime());
        connList.add(conn);
        boolean result = dbService.createSrcDataSourceList(connList);
        assertSame(true, result);
    }

    @Test
    public void testUpdateSrcConnection(){
        SrcDbConnection conn = new SrcDbConnection();
        conn.setId(6);
        conn.setJobId(11);
        conn.setStepId(1);
        conn.setLinkName("my_test");
        conn.setDbHost("127.0.0.2");
        conn.setDbPort("3306");
        conn.setDbName("my_test1");
        conn.setDbUser("test");
        conn.setDbPassword("123456");
        conn.setUpdated(DateUtils.currentDateTime());
        boolean result = dbService.updateSrcDataSource(conn);
        assertSame(true, result);
    }

    @Test
    public void testGetSrcConnections(){
        List<SrcDbConnection> conns = dbService.getSrcDataSources(11, null);
        assertNotNull(conns);
    }

    @Test
    public void testSaveDstConnection(){
        DstDbConnection conn = new DstDbConnection();
        conn.setJobId(11);
        conn.setStepId(1);
        conn.setLinkName("my_test");
        conn.setDbHost("127.0.0.1");
        conn.setDbPort("3306");
        conn.setDbName("my_test");
        conn.setDbUser("test");
        conn.setDbPassword("123456");
        conn.setCreated(DateUtils.currentDateTime());
        conn.setUpdated(DateUtils.currentDateTime());
        boolean result = dbService.createDstDataSource(conn);
        assertSame(true, result);
    }

    @Test
    public void testUpdateDstConnection(){
        DstDbConnection conn = new DstDbConnection();
        conn.setId(7);
        conn.setJobId(11);
        conn.setStepId(1);
        conn.setLinkName("my_test");
        conn.setDbHost("127.0.0.2");
        conn.setDbPort("3306");
        conn.setDbName("my_test1");
        conn.setDbUser("test");
        conn.setDbPassword("123456");
        conn.setUpdated(DateUtils.currentDateTime());
        boolean result = dbService.updateDstDataSource(conn);
        assertSame(true, result);
    }

    @Test
    public void testGetDstConnections(){
        List<DstDbConnection> conns = dbService.getDstDataSources(11, null);
        assertNotNull(conns);
    }
}
