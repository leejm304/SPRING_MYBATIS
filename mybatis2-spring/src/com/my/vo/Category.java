package com.my.vo;

public class Category {
   private int cate_no; //ctrl+shift+y :대문자 -> 소문자
   private int cate_parent_no;
   private String cate_name;
   
   public Category() {
      super();
   }

   public Category(int cate_no, int cate_parent_no, String cate_name) {
      super();
      this.cate_no = cate_no;
      this.cate_parent_no = cate_parent_no;
      this.cate_name = cate_name;
   }

   public int getCate_no() {
      return cate_no;
   }

   public void setCate_no(int cate_no) {
      this.cate_no = cate_no;
   }

   public int getCate_parent_no() {
      return cate_parent_no;
   }

   public void setCate_parent_no(int cate_parent_no) {
      this.cate_parent_no = cate_parent_no;
   }

   public String getCate_name() {
      return cate_name;
   }

   public void setCate_name(String cate_name) {
      this.cate_name = cate_name;
   }

}