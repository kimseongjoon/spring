package com.example.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

@Entity
@Table(name = "item_img_tbl")
@SequenceGenerator(name = "seq1", sequenceName = "seq_item_img_tbl_no", allocationSize = 1)
public class ItemImg {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq1")
  private long no;

  private long itmno;
  @Lob
  private byte[] img;
  @UpdateTimestamp
  private java.sql.Date regdate;

  @Transient
  private String strImg;

  public long getNo() {
    return no;
  }

  public void setNo(long no) {
    this.no = no;
  }


  public long getItmno() {
    return itmno;
  }

  public void setItmno(long itmno) {
    this.itmno = itmno;
  }


  public byte[] getImg() {
    return img;
  }

  public void setImg(byte[] img) {
    this.img = img;
  }


  public java.sql.Date getRegdate() {
    return regdate;
  }

  public void setRegdate(java.sql.Date regdate) {
    this.regdate = regdate;
  }


  public String getStrImg() {
    return (strImg == null) ? "" : strImg;
  }

  public void setStrImg(String strImg) {
    this.strImg = strImg;
  }
}
