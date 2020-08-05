package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.CatBoardUploadDAO;
import jdbcUtil.DiaryDAO;
import vo.CatBoardUploadVO;

@Service
public class CatBoardUploadServiceImpl implements CatBoardUploadService{
	@Autowired
	CatBoardUploadDAO dao;

	@Override
	public List<CatBoardUploadVO> selectList(int seq) {
		return dao.selectList(seq);
	}

	@Override
	public int insert(CatBoardUploadVO uvo) {
		return dao.insert(uvo);
	}
	
	

} 
