package com.zsbatech.baasKettleManager.util;

import javafx.scene.control.TreeItem;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.encryption.Encr;
import org.pentaho.di.core.util.Utils;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.variables.Variables;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.www.*;
import org.w3c.dom.Node;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/30
 */
public class StopJobUtil {

    private static final String HTTP = "http";
    private static final String HTTPS = "https";

    private ReadWriteLock lock;

    private String name;

    private String hostname;

    private String port;

    private String webAppName;

    private String username;

    private String password;

    private String proxyHostname;

    private String proxyPort;

    private boolean master;

    private boolean shared;

    private ObjectId id;

    private boolean sslMode;

    private TreeItem jobParentItem;

    private SslConfiguration sslConfig;

    private VariableSpace variables = new Variables();

    public static final String SSL_MODE_TAG = "sslMode";


    public String getName() {
        return name;
    }

    public String getHostname() {
        return hostname;
    }

    public String getPort() {
        return port;
    }

    public String getWebAppName() {
        return webAppName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProxyHostname() {
        return proxyHostname;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public boolean isMaster() {
        return master;
    }

    public boolean isShared() {
        return shared;
    }

    public ObjectId getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setWebAppName(String webAppName) {
        this.webAppName = webAppName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProxyHostname(String proxyHostname) {
        this.proxyHostname = proxyHostname;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setSslMode(boolean sslMode) {
        this.sslMode = sslMode;
    }

    public SlaveServerJobStatus getCarteObjectId(String jobName, String id){
         return new SlaveServerStatus().findJobStatus(jobName ,id);
    }

    public WebResult stopJob(String transName, String carteObjectId) throws Exception {
        String xml = execService(StopJobServlet.CONTEXT_PATH + "/?name=" + URLEncoder.encode(transName, "UTF-8") + "&xml=Y&id=" + Const.NVL(carteObjectId, ""));
        return WebResult.fromXMLString(xml);
    }

    public String execService(String service) throws Exception {
        HttpGet method = buildExecuteServiceMethod(service, new HashMap<>());
        method.releaseConnection();
        return "SUCCESS";
    }

    public HttpGet buildExecuteServiceMethod(String service, Map<String, String> headerValues) {
        HttpGet method = new HttpGet(constructUrl(service));
        for (String key : headerValues.keySet()) {
            method.setHeader(key, headerValues.get(key));
        }
        return method;
    }

    public String constructUrl(String serviceAndArguments) {
        String realHostname = "localhost";
        if (realHostname.equals("localhost")) {
            realHostname = "127.0.0.1";
        }

        if (!StringUtils.isBlank(webAppName)) {
            serviceAndArguments = "/" + environmentSubstitute(getWebAppName()) + serviceAndArguments;
        }

        String result =
                (isSslMode() ? HTTPS : HTTP) + "://" + realHostname + getPortSpecification() + serviceAndArguments;
        result = Const.replace(result, " ", "%20");
        return result;
    }

    public String environmentSubstitute(String aString) {
        return variables.environmentSubstitute(aString);
    }

    public boolean isSslMode() {
        return sslMode;
    }

    public String getPortSpecification() {
        String realPort = environmentSubstitute( port );
        String portSpec = ":" + realPort;
        if ( Utils.isEmpty( realPort ) || port.equals( "80" ) ) {
            portSpec = "";
        }
        return portSpec;
    }
    public StopJobUtil(){
        initializeVariablesFrom( null );
        id = null;
        lock = new ReentrantReadWriteLock();
    }
    public StopJobUtil( Node slaveNode ) {
        this();
        this.name = XMLHandler.getTagValue( slaveNode, "name" );
        this.hostname = XMLHandler.getTagValue( slaveNode, "hostname" );
        this.port = XMLHandler.getTagValue( slaveNode, "port" );
        this.webAppName = XMLHandler.getTagValue( slaveNode, "webAppName" );
        this.username = XMLHandler.getTagValue( slaveNode, "username" );
        this.password = Encr.decryptPasswordOptionallyEncrypted( XMLHandler.getTagValue( slaveNode, "password" ) );
        this.proxyHostname = XMLHandler.getTagValue( slaveNode, "proxy_hostname" );
        this.proxyPort = XMLHandler.getTagValue( slaveNode, "proxy_port" );
                "Y".equalsIgnoreCase( XMLHandler.getTagValue( slaveNode, "override_existing_properties" ) );
        this.master = "Y".equalsIgnoreCase( XMLHandler.getTagValue( slaveNode, "master" ) );

        setSslMode( "Y".equalsIgnoreCase( XMLHandler.getTagValue( slaveNode, SSL_MODE_TAG ) ) );
        Node sslConfig = XMLHandler.getSubNode( slaveNode, SslConfiguration.XML_TAG );
        if ( sslConfig != null ) {
            setSslMode( true );
            this.sslConfig = new SslConfiguration( sslConfig );
        }
    }
    public void initializeVariablesFrom( VariableSpace parent ) {
        variables.initializeVariablesFrom( parent );
    }

}
