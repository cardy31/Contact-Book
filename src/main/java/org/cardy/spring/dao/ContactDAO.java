package org.cardy.spring.dao;

import org.cardy.spring.model.Contact;

import java.util.List;

/**
 * Defines DAO operations for the contact model.
 * @author Rob Cardy
 */
public interface ContactDAO {

    public void saveOrUpdate(Contact contact);

    public void delete(int contactId);

    public Contact get(int contactId);

    public List<Contact> list();
}
