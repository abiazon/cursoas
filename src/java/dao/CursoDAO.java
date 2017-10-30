/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Curso;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author bruno
 */
public class CursoDAO {
   
public void addCurso(Curso curso) {
        Transaction tx = null;
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(curso);
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

    public List<Curso> listCurso() {
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        List lista = session.createQuery("from Curso").list();
        return lista;
    }
    
    public void remove(Curso curso) {
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
        session.delete(curso);
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
    
    public void update(Curso curso) {
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(curso);
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
    
    public void deleteCurso(int idcurso) {
    }

    public void updateCurso(Curso curso) {
    }

    public Curso getCursoID(int idcurso) {
        Curso curso = null;
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        try{
            String queryString = "from curso where idcurso = :idToFind";
            Query query = session.createQuery(queryString);
            query.setInteger("idToFind", idcurso);
            curso = (Curso) query.uniqueResult();
        }catch (RuntimeException e){
            e.printStackTrace();
        }finally{
            session.flush();
            session.close();            
        }  
        return curso;
    }   
}
