package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.FilesDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/22
 */
public interface FilesDOMapper {

    int insert(FilesDO filesVO);

    int insertBatch(List<FilesDO> filesVOList);

    FilesDO selectByName(String fileName);

    List<FilesDO> queryFile(@Param("createUser")String createUser,@Param("fileName")String fileName);

    List<FilesDO>  queryFiles(@Param("fileCatalog") String fileCatalog,@Param("createUser")String code);

    FilesDO getFileByFileId(@Param("id") Integer fileId);

    List<FilesDO> getFilesByFileIds(@Param("ids") List<Integer> fileIds);

    List<FilesDO> selectFilesVOByName(List<String> FileName);

    List<FilesDO> selectFilesVOByCatalogId(int id);
}
