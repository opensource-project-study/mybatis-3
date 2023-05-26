package org.apache.ibatis.local.test.dao;

import org.apache.ibatis.local.test.model.PersonInfo;

/**
 * @author yuwenbo@kkworld.com
 * @date 2023/5/26
 */
public interface PersonMapper {

  PersonInfo getById(int id);

  int update(PersonInfo personInfo);

}
