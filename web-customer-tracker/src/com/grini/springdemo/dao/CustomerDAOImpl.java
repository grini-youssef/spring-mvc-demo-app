package com.grini.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.grini.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query ... sort by lastName
		Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		//execute query and get result
		List<Customer> customers = query.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save/update the customer
		currentSession.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		//get the customer
		Customer customer = currentSession.get(Customer.class, theId);
		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
//		Customer customer = currentSession.get(Customer.class, theId);
//		
//		//delete the customer
//		currentSession.delete(customer);
		Query query = currentSession.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", theId);
		query.executeUpdate();
		
				
		
	}

	
	
}
