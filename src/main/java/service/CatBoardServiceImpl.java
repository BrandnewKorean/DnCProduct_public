package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.CatBoardDAO;
import vo.CatBoardVO;

@Service
public class CatBoardServiceImpl implements CatBoardService {
	
	@Autowired
	CatBoardDAO dao;
	
	@Override
	public List<CatBoardVO> selectList(){
		return dao.selectList();
	} // selectList
	
	
	@Override
	public CatBoardVO selectOne(CatBoardVO bv) {
		return dao.selectOne(bv);
	} // selectOne
	
	@Override
	public int insert(CatBoardVO bv) {
		return dao.insert(bv);
	} // insert
	

} // CatBoardServiceImpl
