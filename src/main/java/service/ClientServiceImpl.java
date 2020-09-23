package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcUtil.ClientDAO;
import vo.ClientVO;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	ClientDAO dao;

	@Override
	public List<ClientVO> selectList() {
		return dao.selectList();
	}

	@Override
	public ClientVO selectOne(ClientVO cv) {
		return dao.selectOne(cv);
	}

	@Override
	public int insert(ClientVO cv) {
		return dao.insert(cv);
	}
	
	@Override
	public int update(ClientVO cv) {
		return dao.update(cv);
	}
	
	@Override
	public int delete(ClientVO cv) {
		return dao.delete(cv);
	}
	
	@Override
	public ClientVO sendFindId(ClientVO cv) {
		return dao.sendFindId(cv);
	}
	
	@Override
	public ClientVO sendFindPw(ClientVO cv) {
		return dao.sendFindPw(cv);
	}
	
	@Override
	public int passwordChange(ClientVO cv) {
		return dao.update(cv);
	}
} // class
