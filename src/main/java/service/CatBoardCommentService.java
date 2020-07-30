package service;

import java.util.List;

import vo.CatBoardCommentVO;
import vo.CatBoardVO;

public interface CatBoardCommentService {
	
	List<CatBoardCommentVO> selectList(int seq);
	
	CatBoardCommentVO selectOne(CatBoardCommentVO bcv); // selectOne
	
	int insert(CatBoardCommentVO bcv); // insert
	
	int update(CatBoardCommentVO bcv); // update
	
	int delete(CatBoardCommentVO bcv); // delete

} // class
