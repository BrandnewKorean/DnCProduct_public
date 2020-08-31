package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.CatBoardHeartDAO;
import vo.CatBoardHeartVO;

@Service
public class CatBoardHeartServiceImpl implements CatBoardHeartService{

	@Autowired
	CatBoardHeartDAO dao;
	
	@Override
	public int likeCheck(CatBoardHeartVO bhv) {
		return dao.likeCheck(bhv);
	}

	@Override
	public int likeinsert(CatBoardHeartVO bhv) {
		return dao.likeinsert(bhv);
	}

	@Override
	public int likedelete(CatBoardHeartVO bhv) {
		return dao.likedelete(bhv);
	}

	@Override
	public CatBoardHeartVO selectlike(CatBoardHeartVO bhv) {
		return dao.selectlike(bhv);
	}

}
