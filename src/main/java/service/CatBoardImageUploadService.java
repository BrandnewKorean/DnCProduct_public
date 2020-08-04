package service;

import java.util.List;

import vo.CatBoardImageUploadVO;

public interface CatBoardImageUploadService {

	List<CatBoardImageUploadVO> selectList();
	int insert(CatBoardImageUploadVO uvo);
	int delete(CatBoardImageUploadVO uvo);
	
}
