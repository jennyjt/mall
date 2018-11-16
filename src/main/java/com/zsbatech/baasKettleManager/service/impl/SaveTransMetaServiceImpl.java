package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.service.SaveTransMetaService;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.EngineMetaInterface;
import org.pentaho.di.core.util.Utils;
import org.pentaho.di.core.vfs.KettleVFS;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.trans.TransMeta;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018-09-11
 */
@Service
public class SaveTransMetaServiceImpl implements SaveTransMetaService {

    public boolean save( EngineMetaInterface meta, String fname, boolean export ) {
        EngineMetaInterface lmeta;
        if ( export ) {
            lmeta = (TransMeta) ( (TransMeta) meta ).realClone( false );
        } else {
            lmeta = meta;
        }
        boolean saveStatus = saveMeta( lmeta, fname );

        if ( saveStatus ) {
            return true;
        }

        return saveStatus;
    }
    public boolean saveMeta( EngineMetaInterface meta, String filename ) {
        meta.setFilename( filename );
        if ( Utils.isEmpty( meta.getName() )) {
            meta.nameFromFilename();
        }
        boolean saved = false;
        try {
            String xml = XMLHandler.getXMLHeader() + meta.getXML();

            DataOutputStream dos = new DataOutputStream( KettleVFS.getOutputStream( filename, false ) );
            dos.write( xml.getBytes( Const.XML_ENCODING ) );
            dos.close();

            saved = true;


            meta.setFilename( filename );
            meta.clearChanged();
        } catch ( Exception e ) {
        }
        return saved;
    }
}
