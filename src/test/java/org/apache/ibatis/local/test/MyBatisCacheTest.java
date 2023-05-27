package org.apache.ibatis.local.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.local.test.dao.PersonMapper;
import org.apache.ibatis.local.test.model.PersonInfo;
import org.apache.ibatis.session.Configuration;
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
    Configuration configuration = sqlSessionFactory.getConfiguration();
    System.out.println("本地缓存作用域: " + configuration.getLocalCacheScope());
    System.out.println("二级缓存: " + configuration.isCacheEnabled());
  }

  @Test
  void testLocalCache() {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

    // 如果localCacheScope设置为SESSION，第一次从数据库查询，后续命中本地缓存，从本地缓存查询。（可以通过DEBUG日志观察。更改log4j.properties日志配置为log4j.logger.org.apache.ibatis=DEBUG）
    // 如果localCacheScope设置为STATEMENT，都会从数据库查询
    System.out.println(personMapper.getById(1));
    System.out.println(personMapper.getById(1));
    System.out.println(personMapper.getById(1));
  }

  // 假设 localCacheScope设置为了SESSION

  @Test
  void testLocalCacheWhenInsert() {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

    // 从数据库查询，并写入本地缓存
    System.out.println(personMapper.getById(1));
    // 从本地缓存查询
    System.out.println(personMapper.getById(1));
    // 清除本地缓存
    System.out.println("增加了" + personMapper.insert(new PersonInfo().setName("臧洲艺").setAge(23)) + "条记录");
    // 从数据库查询，并写入本地缓存
    System.out.println(personMapper.getById(1));
    // 从本地缓存查询
    System.out.println(personMapper.getById(1));
  }

  @Test
  void testLocalCacheWhenUpdate() {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

    System.out.println(personMapper.getById(1));
    System.out.println(personMapper.getById(1));
    // 清除本地缓存
    System.out.println("更新了" + personMapper.insert(new PersonInfo().setId(1).setAge(23)) + "条记录");
    System.out.println(personMapper.getById(1));
    System.out.println(personMapper.getById(1));
  }

  @Test
  void testLocalCacheWhenDelete() {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

    System.out.println(personMapper.getById(1));
    System.out.println(personMapper.getById(1));
    // 即使删除记录失败，也会清除本地缓存。
    System.out.println("删除了" + personMapper.delete(5) + "条记录");
    System.out.println(personMapper.getById(1));
    System.out.println(personMapper.getById(1));
  }

  @Test
  void testLocalCacheWithMoreSessions() {
    SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
    SqlSession sqlSession2 = sqlSessionFactory.openSession(true);

    // sqlSession1  从数据库查询，并写入本地缓存
    System.out.println("sqlSession1 " + sqlSession1.getMapper(PersonMapper.class).getById(1));
    // sqlSession2  从数据库查询，并写入本地缓存
    System.out.println("sqlSession2 " + sqlSession2.getMapper(PersonMapper.class).getById(1));
    // sqlSession2  从本地缓存查询
    System.out.println("sqlSession2 " + sqlSession2.getMapper(PersonMapper.class).getById(1));
    // sqlSession2  清除本地缓存
    System.out.println("sqlSession2会话中更新了" + sqlSession2.getMapper(PersonMapper.class).update(new PersonInfo().setId(2).setAge(22)) + "条记录");
    // sqlSession1  从本地缓存查询
    // 因为在sqlSession2中更新了记录，此时sqlSession1取的是本地缓存数据，那么就会出现脏数据
    System.out.println("sqlSession1 " + sqlSession1.getMapper(PersonMapper.class).getById(1));
    // sqlSession2  从数据库查询，并写入本地缓存
    System.out.println("sqlSession2 " + sqlSession2.getMapper(PersonMapper.class).getById(1));
    System.out.println("sqlSession2 " + sqlSession2.getMapper(PersonMapper.class).getById(1));
  }

}
