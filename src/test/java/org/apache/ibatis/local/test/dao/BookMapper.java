package org.apache.ibatis.local.test.dao;

import org.apache.ibatis.local.test.model.BookInfo;

/**
 * @author yuwenbo@kkworld.com
 * @date 2023/5/28
 */
public interface BookMapper {

  BookInfo getById(int id);

  int insert(BookInfo bookInfo);

  int update(BookInfo bookInfo);

  int delete(int id);
}
