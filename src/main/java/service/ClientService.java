package service;

import java.util.List;

import vo.ClientVO;

public interface ClientService {
	
	List<ClientVO> selectList(); 
	ClientVO selectOne(ClientVO cv);
	int insert(ClientVO cv);
	int update(ClientVO cv);
	int delete(ClientVO cv);
	ClientVO sendFindId(ClientVO cv);
	ClientVO sendFindPw(ClientVO cv);
	int passwordChange(ClientVO cv);
} // interface
