/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.DL.DLInterfaces;

import java.util.List;

/**
 *
 * @author simon
 * @param <T>
 */
public interface IDAOGeneric<T>
{

	public void create(T entity);

	public void update(T entity);

	public void delete(T entity);

//	public List getAll(Class<T> clazz);
//
//	public T get(Class<T> clazz, int id);

}
