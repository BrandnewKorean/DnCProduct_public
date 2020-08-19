package service;

import java.util.List;

import vo.ProductVO;

public interface ProductService {
	List<ProductVO> selectList(ProductVO pv);
	ProductVO selectOne(ProductVO pv);
}
