package service;

import java.util.List;

import vo.CatBoardVO;

public interface CatBoardService {
	
	List<CatBoardVO> selectList(); // selectList
	
	CatBoardVO selectOne(CatBoardVO bv); // selectOne
	
	int insert(CatBoardVO bv); // insert

} // interface
