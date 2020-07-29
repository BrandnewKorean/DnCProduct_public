	package service;
	
	import java.util.List;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	
	import jdbcUtil.CatBoardCommentDAO;
	import vo.CatBoardCommentVO;
	
	@Service
	public class CatBoardCommentServiceImpl implements CatBoardCommentService {
		
		@Autowired
		CatBoardCommentDAO dao;
		
		@Override
		public int delete(CatBoardCommentVO bcv) {
			return dao.delete(bcv);
		}
		
		
		@Override
		public CatBoardCommentVO selectOne(CatBoardCommentVO bcv) {
			return dao.selectOne(bcv);
		}
		
		@Override
		public List<CatBoardCommentVO> selectList(int seq){
			return dao.selectList(seq);
		}
		
		@Override
		public int insert(CatBoardCommentVO bcv) {
			return dao.insert(bcv);
		}
		
		@Override
		public int update(CatBoardCommentVO bcv) {
			return dao.update(bcv);
		}
		
	}//CatBoardCommentServiceImpl
