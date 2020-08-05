package service;

import java.util.List;

import vo.CatBoardImageUploadVO;

public interface CatBoardImageUploadService {

	List<CatBoardImageUploadVO> selectList(int seq);
	int insert(CatBoardImageUploadVO uvo);
	
}
