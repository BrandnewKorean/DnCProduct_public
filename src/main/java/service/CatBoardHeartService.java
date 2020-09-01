package service;

import vo.CatBoardHeartVO;

public interface CatBoardHeartService {
	
	int likeCheck(CatBoardHeartVO bhv);
	
	int likeinsert(CatBoardHeartVO bhv);
	
	int likedelete(CatBoardHeartVO bhv);
	
	CatBoardHeartVO selectlike(CatBoardHeartVO bhv);
}
