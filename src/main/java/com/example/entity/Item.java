package com.example.entity;

import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@ToString
@Entity
@Table(name = "itemtbl")
@SequenceGenerator(name = "seq_jpa", sequenceName = "seq_item_itmno", initialValue = 1, allocationSize = 1)
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
  generator = "seq_jpa")
  private long itmno;
  private String itmname;
  private String itmcontent;
  private String itmprice;
  @CreationTimestamp
  private java.sql.Date itmdate;

  public long getItmno() {
    return itmno;
  }

  public void setItmno(long itmno) {
    this.itmno = itmno;
  }


  public String getItmname() {
    return itmname;
  }

  public void setItmname(String itmname) {
    this.itmname = itmname;
  }


  public String getItmcontent() {
    return itmcontent;
  }

  public void setItmcontent(String itmcontent) {
    this.itmcontent = itmcontent;
  }


  public String getItmprice() {
    return itmprice;
  }

  public void setItmprice(String itmprice) {
    this.itmprice = itmprice;
  }


  public java.sql.Date getItmdate() {
    return itmdate;
  }

  public void setItmdate(java.sql.Date itmdate) {
    this.itmdate = itmdate;
  }

}
