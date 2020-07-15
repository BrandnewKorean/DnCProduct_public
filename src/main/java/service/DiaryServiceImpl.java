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
	
	@Override
	public int insert(DiaryVO dv) {
		return dao.insert(dv);
	}

	@Override
	public int delete(DiaryVO dv) {
		return dao.delete(dv);
	}

	@Override
	public int update(DiaryVO dv) {
		return dao.update(dv);
	}
}
