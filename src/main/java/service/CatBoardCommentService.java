package service;

import java.util.List;

import vo.CatBoardCommentVO;

public interface CatBoardCommentService {
	
	List<CatBoardCommentVO> select(int seq);
	
	int insert(CatBoardCommentVO bcv);

} // class
