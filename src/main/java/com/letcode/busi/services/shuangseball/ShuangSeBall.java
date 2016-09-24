package com.letcode.busi.services.shuangseball;

import java.io.Serializable;

/**
 * 双色球bean信息
 * 
 * @author chensj
 *
 */
public class ShuangSeBall implements Serializable {

	private static final long	serialVersionUID	= 1L;
	private String				date_no;
	private Integer				sale_count;
	private double				sale_amt;
	private String				red_ball1;
	private String				red_ball2;
	private String				red_ball3;
	private String				red_ball4;
	private String				red_ball5;
	private String				red_ball6;
	private String				blue_ball1;
	private String				blue_ball2;
	private String				sysdate;

	public String getDate_no() {
		return date_no;
	}

	public void setDate_no(String date_no) {
		this.date_no = date_no;
	}

	public Integer getSale_count() {
		return sale_count;
	}

	public void setSale_count(Integer sale_count) {
		this.sale_count = sale_count;
	}

	public double getSale_amt() {
		return sale_amt;
	}

	public void setSale_amt(double sale_amt) {
		this.sale_amt = sale_amt;
	}

	public String getRed_ball1() {
		return red_ball1;
	}

	public void setRed_ball1(String red_ball1) {
		this.red_ball1 = red_ball1;
	}

	public String getRed_ball2() {
		return red_ball2;
	}

	public void setRed_ball2(String red_ball2) {
		this.red_ball2 = red_ball2;
	}

	public String getRed_ball3() {
		return red_ball3;
	}

	public void setRed_ball3(String red_ball3) {
		this.red_ball3 = red_ball3;
	}

	public String getRed_ball4() {
		return red_ball4;
	}

	public void setRed_ball4(String red_ball4) {
		this.red_ball4 = red_ball4;
	}

	public String getRed_ball5() {
		return red_ball5;
	}

	public void setRed_ball5(String red_ball5) {
		this.red_ball5 = red_ball5;
	}

	public String getRed_ball6() {
		return red_ball6;
	}

	public void setRed_ball6(String red_ball6) {
		this.red_ball6 = red_ball6;
	}

	public String getBlue_ball1() {
		return blue_ball1;
	}

	public void setBlue_ball1(String blue_ball1) {
		this.blue_ball1 = blue_ball1;
	}

	public String getBlue_ball2() {
		return blue_ball2;
	}

	public void setBlue_ball2(String blue_ball2) {
		this.blue_ball2 = blue_ball2;
	}

	public String getSysdate() {
		return sysdate;
	}

	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}

}
