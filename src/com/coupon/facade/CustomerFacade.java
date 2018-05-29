package com.coupon.facade;

import java.util.Collection;

import com.coupon.beans.Coupon;
import com.coupon.beans.CouponType;
import com.coupon.beans.Customer;
import com.coupon.couponDAO.CouponDAO;
import com.coupon.couponDAO.CouponDBDAO;
import com.coupon.couponDAO.CustomerDAO;
import com.coupon.couponDAO.CustomerDBDAO;
import com.coupon.exception.MyException;



public class CustomerFacade implements CouponClientFacade {

	private CustomerDAO customerDAO = null;
	private CouponDAO couponDAO =null ; 
	
	public CustomerFacade() {
		this.customerDAO = new CustomerDBDAO();
		this.couponDAO = new CouponDBDAO();
		
		
	}
	
	@Override
	public CouponClientFacade login(String name, String password, UserType userType) throws MyException {
		if(customerDAO.login(name, password)) {
			System.out.println("login customer seccessed!");
			return this;
		}
		System.out.println("login customer failed");

		return null;
	}
	
	public void purchaseCoupon (Coupon coupon , Customer customer) throws MyException
	{
		try {
			couponDAO.getCoupon(coupon.getId());
		} catch (MyException e) {
			throw new MyException ("there is a problem eith the database.");
		}
		customerDAO.customerPurchaseCoupon(coupon, customer);
	}
	
	
	public Collection<Coupon> getAllPurchasedCoupons(Customer customer) throws MyException {
		
			return customerDAO.getAllCoupons(customer.getId());
			
		
	}
	
	
	public Collection<Coupon> getAllPurchasedCouponsByType(Customer customer,CouponType couponType) throws MyException {
		
		return customerDAO.getCouponsByType(customer.getId() ,couponType);
	}
	
	public Collection<Coupon> getAllPurchasedCouponsByPrice(Customer customer , double price) throws MyException {
		
		return customerDAO.getCouponsByPrice(customer.getId() ,price);
	}
	
	
	
	
	

}
