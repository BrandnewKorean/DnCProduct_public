package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.DiaryUploadDAO;
import vo.DiaryUploadVO;

@Service
public class DiaryUploadServiceImpl implements DiaryUploadService{
	@Autowired
	DiaryUploadDAO dao;
	
	@Override
	public List<DiaryUploadVO> selectList(DiaryUploadVO duv) {
		return dao.selectList(duv);
	}
	
	@Override
	public int insert(DiaryUploadVO duv) {
		return dao.insert(duv);
	}
}
