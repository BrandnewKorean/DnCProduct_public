package service;

import java.util.List;

import vo.CatBoardNoticeVO;

public interface CatBoardNoticeService {
	List<CatBoardNoticeVO> selectList();
	CatBoardNoticeVO selectOne(CatBoardNoticeVO bnv);
	int countUp(CatBoardNoticeVO bnv);
}
