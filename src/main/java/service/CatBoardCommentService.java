package service;

import java.util.List;

import vo.CatBoardCommentVO;

public interface CatBoardCommentService {

	CatBoardCommentVO selectOne(CatBoardCommentVO bcv);
	
	List<CatBoardCommentVO> selectList(int seq);
	
	int insert(CatBoardCommentVO bcv);
	
	int update(CatBoardCommentVO bcv);
	
	int delete(CatBoardCommentVO bcv);
	
	
}//interface
