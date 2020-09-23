package service;


import java.util.List;

import vo.BasketVO;

public interface BasketService {

	List<BasketVO> selectList(BasketVO bkv);
	
	BasketVO selectOne(BasketVO bkv);
	
	int insert(BasketVO bkv);
	
	int delete(BasketVO bkv);
	
}
