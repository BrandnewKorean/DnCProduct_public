package service;

import java.util.List;

import vo.DiaryVO;

public interface DiaryService {
	List<DiaryVO> selectList(DiaryVO dv);
	DiaryVO selectOne(DiaryVO dv);
	int insert(DiaryVO dv);
	int delete(DiaryVO dv);
	int update(DiaryVO dv);
}