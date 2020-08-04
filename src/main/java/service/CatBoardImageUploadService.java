package service;

import java.util.List;

import vo.CatBoardImageUploadVO;

public interface CatBoardImageUploadService {
	
	List<CatBoardImageUploadVO> selectList(CatBoardImageUploadVO uvo);
	int insert(CatBoardImageUploadVO uvo);

}
