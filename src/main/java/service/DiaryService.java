package service;

import vo.DiaryVO;

public interface DiaryService {
	DiaryVO selectOne(DiaryVO dv);
	int insert(DiaryVO dv);
	int delete(DiaryVO dv);
	int update(DiaryVO dv);
}