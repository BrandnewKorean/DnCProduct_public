package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.CatBoardNoticeDAO;
import vo.CatBoardNoticeVO;

@Service
public class CatBoardNoticeServiceImpl implements CatBoardNoticeService{
	@Autowired
	CatBoardNoticeDAO dao;
	
	@Override
	public List<CatBoardNoticeVO> selectList() {
		return dao.selectList();
	}

	@Override
	public CatBoardNoticeVO selectOne(CatBoardNoticeVO bnv) {
		return dao.selectOne(bnv);
	}

	@Override
	public int countUp(CatBoardNoticeVO bnv) {
		return dao.countUp(bnv);
	}
}
