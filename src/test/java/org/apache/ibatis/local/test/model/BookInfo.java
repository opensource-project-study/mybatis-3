package org.apache.ibatis.local.test.model;

import java.io.Serializable;

/**
 * @author Yu Wenbo
 * @date 2021/1/4 23:31
 */
public class BookInfo implements Serializable {

  private static final long serialVersionUID = 8380239880550892466L;

  private Integer id;
  private String bookName;
  private Float bookPrice;

  public Integer getId() {
    return id;
  }

  public BookInfo setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getBookName() {
    return bookName;
  }

  public BookInfo setBookName(String bookName) {
    this.bookName = bookName;
    return this;
  }

  public Float getBookPrice() {
    return bookPrice;
  }

  public BookInfo setBookPrice(Float bookPrice) {
    this.bookPrice = bookPrice;
    return this;
  }

  @Override
  public String toString() {
    return "BookInfo{" + "id=" + id + ", bookName='" + bookName + '\'' + ", bookPrice=" + bookPrice + "}";
  }

}
