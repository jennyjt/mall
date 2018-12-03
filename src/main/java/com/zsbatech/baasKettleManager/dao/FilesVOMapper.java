package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.FilesVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/22
 */
public interface FilesVOMapper {

    int insert(FilesVO filesVO);

    int insertBatch(List<FilesVO> filesVOList);

    FilesVO selectByName(String fileName);

    List<FilesVO> queryFile(@Param("createUser")String createUser,@Param("fileName")String fileName);

    List<FilesVO>  queryFiles(@Param("fileCatalog") String fileCatalog,@Param("createUser")String code);

    FilesVO getFileByFileId(@Param("id") Integer fileId);

    List<FilesVO> getFilesByFileIds(@Param("ids") List<Integer> fileIds);

    List<FilesVO> selectFilesVOByName(List<String> FileName);
}
