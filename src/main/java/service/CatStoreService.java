package service;

import java.util.List;

import vo.CatStoreVO;

public interface CatStoreService {
	List<CatStoreVO> selectList(CatStoreVO cs);
}
