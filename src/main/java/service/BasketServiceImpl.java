package service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.BasketDAO;
import vo.BasketVO;

@Service
public class BasketServiceImpl implements BasketService{

	@Autowired
	BasketDAO dao;

	@Override
	public List<BasketVO> selectList(BasketVO bkv) {
		return dao.selectList(bkv);
	}
	
	@Override
	public int insert(BasketVO bkv) {
		return dao.insert(bkv);
	}
	
	@Override
	public int delete(BasketVO bkv) {
		return dao.delete(bkv);
	}

	@Override
	public BasketVO selectOne(BasketVO bkv) {
		return dao.selectOne(bkv);
	}

	

	


}
