package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.CatBoardCommentDAO;
import vo.CatBoardCommentVO;

@Service
public class CatBoardCommentServiceImpl implements CatBoardCommentService {
	
	@Autowired
	CatBoardCommentDAO dao;
	
	@Override
	public List<CatBoardCommentVO> select(int seq){
		return dao.select(seq);
	}
	
	@Override
	public int insert(CatBoardCommentVO cbv) {
		return dao.insert(cbv);
	}
	
}//CatBoardCommentServiceImpl
