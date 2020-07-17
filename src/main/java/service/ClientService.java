package service;

import java.util.List;

import vo.ClientVO;

public interface ClientService {
	
	List<ClientVO> selectList(); 
	ClientVO selectOne(ClientVO cv);
	int insert(ClientVO cv);
	int update(ClientVO cv);
	int delete(ClientVO cv);
} // interface
