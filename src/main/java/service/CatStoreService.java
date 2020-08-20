package service;

import java.util.List;

import searchCriteria.Search;
import vo.CatStoreVO;

public interface CatStoreService {
	List<CatStoreVO> selectList(CatStoreVO cs);
	int searchRowCount(Search search);
}
