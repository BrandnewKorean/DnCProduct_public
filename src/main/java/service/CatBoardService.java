package service;

import java.util.List;

import searchCriteria.Search;
import vo.CatBoardVO;
import vo.PageVO;

public interface CatBoardService {
	List<CatBoardVO> searchList(Search search);
	
	int searchRowCount(Search search);
	
	int totalRowCount();
	
	List<CatBoardVO> criList(Search search);
	
	List<CatBoardVO> selectList(); // selectList
	
	CatBoardVO selectOne(CatBoardVO bv); // selectOne
	
	int countUp(CatBoardVO bv); // countUp
	
	int insert(CatBoardVO bv); // insert
	
	int update(CatBoardVO bv); // update
	
	int delete(CatBoardVO bv); // delete
	
	PageVO<CatBoardVO> pageList(PageVO<CatBoardVO> pvo); //pageList()
} // interface
