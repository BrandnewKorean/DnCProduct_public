package service;

import java.util.List;

import vo.DiaryUploadVO;

public interface DiaryUploadService {
	List<DiaryUploadVO> selectList(DiaryUploadVO duv);
	int insert(DiaryUploadVO duv);
}
