package com.zsbatech.baasTestManager;

import com.zsbatech.baasKettleManager.model.MyProduct;
import com.zsbatech.base.common.Pagination;
import org.junit.Test;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobHopMeta;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.special.JobEntrySpecial;
import org.pentaho.di.job.entries.trans.JobEntryTrans;
import org.pentaho.di.job.entry.JobEntryCopy;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.insertupdate.InsertUpdateMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {


    @Test
    public void test() throws Exception {
        KettleEnvironment.init();
        TransMeta transMeta = new TransMeta("C:\\Users\\zhang\\Desktop\\aaa.ktr");
        Trans trans = new Trans(transMeta);
        trans.prepareExecution(null);
        trans.startThreads();
        trans.waitUntilFinished();
        if (trans.getErrors() != 0) {
            System.out.println("Error encountered");
        }
        TableInputMeta tableInputMeta = new TableInputMeta();
    }

    @Test
    public void testJob() throws Exception {
        KettleEnvironment.init();
        JobMeta jobMeta = new JobMeta("/Users/yinshanzhang/Desktop/cads.kjb", null);
        Job job = new Job(null, jobMeta);
        job.start();
        Thread.currentThread().setName("cads");
        job.waitUntilFinished();
        if (job.getErrors() != 0) {
            System.out.println("Error encountered");
        }
    }


    @Test
    public void TestFile() throws Exception {
//        FTPClient ftpClient = FTPUtil.loginFTP("106.75.17.46", 21, "test", "test123456");
//        ftpClient.makeDirectory("files/pento/dataSour");
    }


    @Test
    public  void  ss(){
        System.out.println("jjjjjj");
//        JSON.toJSON()
    }

    @Test
    public void getProductList() {
        List<MyProduct> productList = new ArrayList<MyProduct>();


        Connection conn;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?allowMultiQueries=true&serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "root";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement();


            String sql = "select list_picture_path,pname,price from my_product;";

            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
//            System.out.println(rsmd);
            int colnum=rsmd.getColumnCount();

            while(rs.next()){
                MyProduct product = new MyProduct();
               /*
                product.setPname(rs.getString("pname"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setListPicturePath("list_picture_path" );

*/
               for (int i=1;i<=colnum;i++){
                   Object obj=rs.getObject(i);
                   System.out.println(obj.toString());

               }



                productList.add(product);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Pagination<MyProduct> pagination=new Pagination<>(productList);
//        System.out.println(pagination.toString());




    }
    @Test
    public void sssss(){
        MyProduct product=new MyProduct();

        Field [] field=product.getClass().getDeclaredFields();
      for(int i=0; i<field.length;i++){
//          System.out.println(field[i]);
      }
        try {
            System.out.println(field[0]);
            field[1].set(product,"aa");
            System.out.println(product.getPname());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void ssss(){
        int a=1;
        String s= ""+a;
        System.out.println(s);
    }




}
