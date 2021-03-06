package kr.co.jhta.util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {

	/*
	 * mybatis의 중 api
	 * -sqlSession
	 * 	매퍼 파일에 정의된 sql 문을 실행하는 객체
	 *  int insert(String id)
	 *  int insert(String id, Object parameter)
	 *  int update(String id)
	 *  int update(String id, Object parameter)
	 *  int delete(Stiring id)
	 *  int delete(Stiring id, Object paramter)
	 *  Object selectOne(String id)
	 *  Object selectOne(String id,Object paramter)
	 *  List selectList(string id)
	 *  List selectList(string id, Object paramter)
	 *  void close()
	 *  void commit()
	 *  void rollback()
	 *  쿼리를 한 번 실행 할 때마다 SqlSessionFactory로부터 새로운 SqlSession 객체를 획득해서 사용, 폐기한다.
	 *  
	 *  
	 * -sqlSessionFactory
	 * 	쿼리 실행에 필요한 SqlSession 객체를 제공하는 객체다.
	 * 	애플리케이션이 실행되는 동안 딱 한번만 생성해서 계속 사용한다.
	 * 	SqlSession openSession()
	 * 	*새로운 Sql Session객체를 반환한다.
	 * 
	 * 
	 * 
	 * -sqlSessionFactoryBuilder
	 * 	mybatis 환경설정파일(/src/main/resources/mybatis/mybatis-config.xml)
	 * 	myBatis 환경설정 정보를 이용해서 SqlSessionFactory 객체를 생성한다.
	 *  SqlSessionFactory build(InputStream inputStream)
	 */
	
	private static SqlSessionFactory sqlSessionFactory;
	
	
	static {
		try {
			String resource = "mybatis/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * sqlSessionFactory 객체를 반환한다.
	 * @return SqlSessionFactory 객체
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	/**
	 * SQL문을 실행하는 SqlSession 객체를 반환한다.
	 * @return SqlSession 객체(INSERT/UPDATE/DELETE/SELECT, 트랜잭션 기능 제공)
	 */
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
	
}
