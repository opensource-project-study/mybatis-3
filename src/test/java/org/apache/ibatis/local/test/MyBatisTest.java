package org.apache.ibatis.local.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.local.test.dao.PersonMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author yuwenbo@kkworld.com
 * @date 2023/5/28
 */
class MyBatisTest {

  private static SqlSessionFactory sqlSessionFactory;

  @BeforeAll
  static void setup() throws Exception {
    final String resource = "org/apache/ibatis/builder/local/TestMapperConfig.xml";
    final Reader reader = Resources.getResourceAsReader(resource);
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
  }

  @Test
  void test1() {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    System.out.println(sqlSession.getMapper(PersonMapper.class).getById(1));
  }
}
