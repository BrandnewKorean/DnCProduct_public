package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.CatStoreDAO;
import searchCriteria.StoreSearch;
import vo.CatStoreVO;

@Service
public class CatStoreServiceImpl implements CatStoreService{
	
	@Autowired
	CatStoreDAO dao;

	@Override
	public List<CatStoreVO> selectList(CatStoreVO cs) {
		return dao.selectList(cs);
	}

	@Override
	public int searchRowCount(StoreSearch search) {
		return dao.searchRowCount(search);
	}

	@Override
	public List<CatStoreVO> searchList(StoreSearch search) {
		return dao.searchList(search);
	}

	@Override
	public CatStoreVO selectOne(CatStoreVO cs) {
		return dao.selectOne(cs);
	}

	@Override
	public List<CatStoreVO> selectTop5(CatStoreVO cs) {
		return dao.selectTop5(cs);
	}
}
