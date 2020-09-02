package service;

import java.util.List;

import vo.ProductImageVO;

public interface ProductImageService {
	List<ProductImageVO> selectList(ProductImageVO piv);
	
	ProductImageVO selectOne(ProductImageVO piv);
}
