package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.DiaryDAO;
import vo.DiaryVO;

@Service
public class DiaryServiceImpl implements DiaryService{
	@Autowired
	DiaryDAO dao;
	
	@Override
	public DiaryVO selectOne(DiaryVO dv) {
		return dao.selectOne(dv);
	}
}
