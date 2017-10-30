/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Cidade;
import modelo.Pessoa;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author adriano
 */
public class CidadeDAO {
    
public List listCidade(String idunidFed) {
        List lista = null;
        Transaction tx = null;
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            String queryString = "from Cidade where idunidFed = :idToFind order by NomeDesc ";
            Query query = session.createQuery(queryString);
            query.setString("idToFind", idunidFed);
            lista = query.list();
        }catch (RuntimeException e){
            e.printStackTrace();
        }finally{
            session.flush();
            session.close();
            
        }  
        return lista;
    
}
}
