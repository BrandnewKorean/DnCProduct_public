package jdbcUtil;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.ClientVO;

@Repository
public class ClientDAO {
	@Autowired
	private SqlSession sqlsession;
	/*
	 * 스프링 컨테이너가 setSqlSessionFactory 메서드를 자동으로 호출하여 스프링 설정파일(..-context.xml)에
	 * <bean> 등록된 SqlSessionFactoryBean 객체를 인자로 받아 부모인 SqlSessionDaoSupport에
	 * setSqlSessionFactory() 메서드로 설정해줌.
	 */
	private static final String NameSpace = "ex01.mappers.ClientMapper.";
	
	
	public List<ClientVO> selectList(){
		return sqlsession.selectList(NameSpace+"selectList");
	}
	
	public ClientVO selectOne(ClientVO cv) {
		return sqlsession.selectOne(NameSpace+"selectOne", cv);
	}
	
	public int insert(ClientVO cv) {
		return sqlsession.insert(NameSpace+"insert",cv);
	}
	
	public int update(ClientVO cv) {
		return sqlsession.update(NameSpace+"update",cv);
	}
	
	public int delete(ClientVO cv) {
		return sqlsession.delete(NameSpace+"delete",cv);
	} // delete

} // class
