package com.erdem.samples;

import java.util.List;

public class TestHibernate {

	public static void main(String[] args) {  
		HibernateUtil.save(new User("1","2"));
		List<User> list = HibernateUtil.getAll();

        for (User res : list) {
			System.out.println(res);
		}
	}  
}
