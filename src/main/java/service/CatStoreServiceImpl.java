package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.CatStoreDAO;
import searchCriteria.Search;
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
	public int searchRowCount(Search search) {
		return dao.searchRowCount(search);
	}
}
