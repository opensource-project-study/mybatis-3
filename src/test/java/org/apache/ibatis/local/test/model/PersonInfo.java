package org.apache.ibatis.local.test.model;

/**
 * @author yuwenbo@kkworld.com
 * @date 2023/5/26
 */
public class PersonInfo {

  private int id;
  private String name;
  private int age;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "PersonInfo{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
  }
}
