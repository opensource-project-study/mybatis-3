package org.apache.ibatis.local.test.dao;

import java.util.List;

import org.apache.ibatis.local.test.model.BookInfo;

/**
 * @author yuwenbo@kkworld.com
 * @date 2023/5/28
 */
public interface PersonBookMapper {

  List<BookInfo> getByPersonId(int personId);
}
