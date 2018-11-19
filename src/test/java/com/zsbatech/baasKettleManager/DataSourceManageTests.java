package com.zsbatech.baasKettleManager;

import com.zsbatech.baasKettleManager.model.DbConnection;
import com.zsbatech.baasKettleManager.service.DataSouceManageService;
import com.zsbatech.base.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
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
    public void testSaveConnection(){
        DbConnection conn = new DbConnection();
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
        boolean result = dbService.createDataSource(conn);
        assertSame(true, result);
    }

    @Test
    public void testUpdateConnection(){
        DbConnection conn = new DbConnection();
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
        boolean result = dbService.updateDataSource(conn);
        assertSame(true, result);
    }

    @Test
    public void testGetConnections(){
        List<DbConnection> conns = dbService.getDataSources(11, null);
        assertNotNull(conns);
    }
}
