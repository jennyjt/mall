package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.FilesVO;

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

    List<FilesVO> queryFilesByfileName(List<String> fileNames);
}
