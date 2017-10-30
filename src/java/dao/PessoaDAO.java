/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Cidade;
import modelo.Estado;
import modelo.Pessoa;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author adriano
 */
public class PessoaDAO {
    public void addPessoa(Pessoa pessoa) {
        Transaction tx = null;
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        tx = session.beginTransaction();
        try {
            session.save(pessoa);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    public List<Pessoa> listPessoa() {
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        List lista = session.createQuery("from Pessoa").list();
        return lista;
    }
    
    public List<Cidade> listCidade(String estado) {
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        String queryString = "from Cidade where idunidFed = :idToFind order by NomeDesc";
        Query query = session.createQuery(queryString);
        query.setString("idToFind", estado);
        List listacidade = query.list(); 
        return listacidade;
    }
    
    public List<Estado> listEstado() {
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        List listaestado = session.createQuery("from Estado").list();
        return listaestado;
    }
    
    public void remove(Pessoa pessoa) {
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(pessoa);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.flush();
            session.close();
        }
    }
    
    public void update(Pessoa pessoa) {
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(pessoa);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.flush();
            session.close();
        }
     }
   
    public void deletePessoa(int idpessoa) {
    }

    public void updatePessoa(Pessoa pessoa) {
    }

    public Pessoa getPessoaID(int idpessoa) {
        Pessoa pessoa = null;
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        try{
            String queryString = "from pessoa where idpessoa = :idToFind";
            Query query = session.createQuery(queryString);
            query.setInteger("idToFind", idpessoa);
            pessoa = (Pessoa) query.uniqueResult();
        }catch (RuntimeException e){
            e.printStackTrace();
        }finally{
            session.flush();
            session.close();
        }  
        return pessoa;
    }

    public Pessoa getPessoaapelido(String apelidopessoa) {
        Pessoa pessoa = null;
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        try{
            String queryString = "from pessoa where apelido = :apelidoToFind";
            Query query = session.createQuery(queryString);
            query.setString("apelidoToFind", apelidopessoa);
            pessoa = (Pessoa) query.uniqueResult();
        }catch (RuntimeException e){
            e.printStackTrace();
        }finally{
            session.flush();
            session.close();
        }  
        return pessoa;
    }
}
