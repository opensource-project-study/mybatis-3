package org.apache.ibatis.local.test.model;

import java.io.Serializable;

/**
 * @author Yu Wenbo
 * @date 2021/1/4 23:27
 */
public class PersonBookInfo implements Serializable {

  private static final long serialVersionUID = -4096048719156404912L;

  private Integer id;
  private Integer personId;
  private Integer bookId;

  public Integer getId() {
    return id;
  }

  public PersonBookInfo setId(Integer id) {
    this.id = id;
    return this;
  }

  public Integer getPersonId() {
    return personId;
  }

  public PersonBookInfo setPersonId(Integer personId) {
    this.personId = personId;
    return this;
  }

  public Integer getBookId() {
    return bookId;
  }

  public PersonBookInfo setBookId(Integer bookId) {
    this.bookId = bookId;
    return this;
  }

  @Override
  public String toString() {
    return "PersonBookInfo{" + "id=" + id + ", personId=" + personId + ", bookId=" + bookId + '}';
  }
}
