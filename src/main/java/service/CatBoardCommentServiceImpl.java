package service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.CatBoardCommentDAO;
import vo.CatBoardCommentVO;
import vo.CatBoardVO;

@Service
public class CatBoardCommentServiceImpl implements CatBoardCommentService{

	@Autowired
	CatBoardCommentDAO dao;
	
	@Override
	public List<CatBoardCommentVO> selectList(int seq){
		return dao.selectList(seq);
	}
	
	@Override
	public CatBoardCommentVO selectOne(CatBoardCommentVO bv) {
		return dao.selectOne(bv);
	} // selectOne
	
	@Override
	public int insert(CatBoardCommentVO bcv) {
		return dao.insert(bcv);
	}
	
	@Override
	public int update(CatBoardCommentVO bcv) {
		return dao.update(bcv);
	}
	
	@Override
	public int delete(CatBoardCommentVO bcv) {
		return dao.delete(bcv);
	}
	
}//CatBoardCommentServiceImpl