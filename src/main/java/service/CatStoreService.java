package service;

import java.util.List;

import searchCriteria.StoreSearch;
import vo.CatStoreVO;

public interface CatStoreService {
	List<CatStoreVO> selectList(CatStoreVO cs);
	int searchRowCount(StoreSearch search);
	List<CatStoreVO> searchList(StoreSearch search);
}
