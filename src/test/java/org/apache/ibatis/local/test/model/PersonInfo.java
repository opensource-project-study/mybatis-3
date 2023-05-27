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

  public PersonInfo setId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public PersonInfo setName(String name) {
    this.name = name;
    return this;
  }

  public int getAge() {
    return age;
  }

  public PersonInfo setAge(int age) {
    this.age = age;
    return this;
  }

  @Override
  public String toString() {
    return "PersonInfo{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
  }
}
