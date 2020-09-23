package service;

import java.util.List;

import vo.CatBoardUploadVO;

public interface CatBoardUploadService {
	List<CatBoardUploadVO> selectList(int seq);
	int insert(CatBoardUploadVO uvo);
	List<CatBoardUploadVO> selectAllList();
}
