package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.CatBoardImageUploadDAO;
import vo.CatBoardImageUploadVO;

@Service
public class CatBoardImageUploadServiceImpl implements CatBoardImageUploadService {

	@Autowired
	CatBoardImageUploadDAO dao;
	
	public List<CatBoardImageUploadVO> selectList(int seq){
		return dao.selectList(seq);
	}
	
	public int insert(CatBoardImageUploadVO uvo) {
		return dao.insert(uvo);
	}
	
}