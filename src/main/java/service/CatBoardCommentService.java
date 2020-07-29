package service;

import java.util.List;

import vo.CatBoardCommentVO;

public interface CatBoardCommentService {

	int delete(CatBoardCommentVO bcv);
	
	CatBoardCommentVO selectOne(CatBoardCommentVO bcv);
	
	List<CatBoardCommentVO> selectList(int seq);
	
	int insert(CatBoardCommentVO bcv);
	
	int update(CatBoardCommentVO bcv);
	
	
	
}//interface
