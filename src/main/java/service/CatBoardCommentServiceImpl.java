package service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.CatBoardCommentDAO;
import vo.CatBoardCommentVO;

@Service
public class CatBoardCommentServiceImpl implements CatBoardCommentService{

	@Autowired
	CatBoardCommentDAO dao;
	
	@Override
	public ArrayList<CatBoardCommentVO> select(int seq){
		return dao.select(seq);
	}
	
	@Override
	public int insert(CatBoardCommentVO bcv) {
		return dao.insert(bcv);
	}
}//CatBoardCommentServiceImpl