package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.CatBoardImageUploadDAO;
import jdbcUtil.DiaryDAO;
import vo.CatBoardImageUploadVO;

@Service
public class CatBoardImageUploadServiceImpl implements CatBoardImageUploadService{
	@Autowired
	CatBoardImageUploadDAO dao;

	@Override
	public List<CatBoardImageUploadVO> selectList(CatBoardImageUploadVO uvo) {
		return dao.selectList(uvo);
	}

	@Override
	public int insert(CatBoardImageUploadVO uvo) {
		return dao.insert(uvo);
	}
	
	

} 
