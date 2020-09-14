package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.ProductImageDAO;
import vo.ProductImageVO;

@Service
public class ProductImageServiceImpl implements ProductImageService{

	@Autowired
	ProductImageDAO dao;
	
	@Override
	public List<ProductImageVO> selectList(ProductImageVO piv) {
		return dao.selectList(piv);
	}

	@Override
	public ProductImageVO selectOne(ProductImageVO piv) {
		return dao.selectOne(piv);
	}

}
