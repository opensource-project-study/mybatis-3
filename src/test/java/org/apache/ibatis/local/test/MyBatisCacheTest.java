package org.apache.ibatis.local.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.local.test.dao.PersonMapper;
import org.apache.ibatis.local.test.model.PersonInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author yuwenbo@kkworld.com
 * @date 2023/5/26
 */
class MyBatisCacheTest {

  private static SqlSessionFactory sqlSessionFactory;

  @BeforeAll
  static void setup() throws Exception {
    final String resource = "org/apache/ibatis/builder/local/TestMapperConfig.xml";
    final Reader reader = Resources.getResourceAsReader(resource);
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
  }

  @Test
  void showDefaultCacheConfiguration() {
    System.out.println("本地缓存范围: " + sqlSessionFactory.getConfiguration().getLocalCacheScope());
    System.out.println("二级缓存是否被启用: " + sqlSessionFactory.getConfiguration().isCacheEnabled());
  }

  @Test
  void testLocalCache() {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
    System.out.println(personMapper.getById(1));
    System.out.println(personMapper.getById(1));
    System.out.println(personMapper.getById(1));
  }

  @Test
  void testLocalCacheWhenUpdate() {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

    PersonInfo personInfo = personMapper.getById(1);
    System.out.println(personInfo);
    personInfo.setName("秀秀");
    System.out.println("更新了" + personMapper.update(personInfo) + "条记录");
    System.out.println(personMapper.getById(1));
  }

}
