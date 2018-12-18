package com.zsbatech.baasKettleManager;


import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.baasKettleManager.model.FtpSourceManager;
import com.zsbatech.baasKettleManager.service.FtpSouceManageService;
import com.zsbatech.baasKettleManager.util.TableUtil;
import com.zsbatech.base.common.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pentaho.di.core.exception.KettleException;
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
        conn.setNickName("测试");
        boolean result = ftpService.createDataSource(conn);
        assertSame(true, result);
    }

    @Test
    public void testUpdateFtpSource(){
        FtpSourceManager conn = new FtpSourceManager();
        conn.setId(2);
        conn.setFtpHost("127.0.0.1");
        conn.setFtpPort("220");
        conn.setUserName("root");
        conn.setPassWord("123456");
        boolean result = ftpService.updateDataSource(conn);
        assertSame(true, result);
    }

    @Test
    public void testGetFtpSources(){
        Pagination<FtpSourceManager> conns = ftpService.getDataSources(1, 2, new FtpSourceManager());
        assertNotNull(conns);
    }

    @Test
    public void testDeleteFtpSource(){
        boolean result = ftpService.deleteDataSource(2);
        assertSame(true, result);
    }

    @Test
    public void testGetTableSql() throws KettleException {
        DbManagement source = new DbManagement();
        source.setLinkName("testOracle");
        source.setDbHost("172.16.0.181");
        source.setDbPort("1521");
        source.setDbName("orcl");
        source.setDbUser("system");
        source.setDbPassword("123456");
        source.setCreateUser("ORACLE");//TODO 临时使用参数
        source.setDbType("ORACLE");

        DbManagement dest = new DbManagement();
        dest.setLinkName("testMysql");
        dest.setDbHost("117.50.10.62");
        dest.setDbPort("3306");
        dest.setDbName("switch_clearing");
        dest.setDbUser("root");
        dest.setDbPassword("Encrypted 2bec2dcaa0be79f9db808a2228ec3f790");
        dest.setCreateUser("MYSQL");
        dest.setDbType("MYSQL");

        //String ddl = TableUtil.getCreateTableDDL(source,dest,"KETTLE1");
        //System.out.println(ddl);
    }

    @Test
    public void testGetTableIndexSql() throws KettleException {
        DbManagement source = new DbManagement();
        source.setLinkName("testOracle");
        source.setDbHost("172.16.0.181");
        source.setDbPort("1521");
        source.setDbName("orcl");
        source.setDbUser("system");
        source.setDbPassword("123456");
        source.setCreateUser("ORACLE");//TODO 临时使用参数
        source.setDbType("ORACLE");

        DbManagement dest = new DbManagement();
        dest.setLinkName("testMysql");
        dest.setDbHost("117.50.10.62");
        dest.setDbPort("3306");
        dest.setDbName("switch_clearing");
        dest.setDbUser("root");
        dest.setDbPassword("Encrypted 2bec2dcaa0be79f9db808a2228ec3f790");
        dest.setCreateUser("MYSQL");
        dest.setDbType("MYSQL");

        String ddl = TableUtil.getCreateIndexDDL(source,dest,"KETTLE");
        System.out.println(ddl);
    }

}
