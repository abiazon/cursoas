/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import modelo.Curso;
import modelo.Inscritoscurso;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author bruno
 */
public class CursoDAO {
    private final Date dataAtual = new Date();   
    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
    
    public void addCurso(Curso curso) {
        Transaction tx = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
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
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        List lista = session.createQuery("from Curso").list();
        session.flush();
        session.close();
        return lista;
    }

    public List<Curso> listCursoVigentes() {
        System.out.println("dataatual:"+formatador.format(dataAtual));
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String queryString = "from Curso where datainiciocurso >= :idToFind";
        Query query = session.createQuery(queryString);
        query.setDate("idToFind", dataAtual);
        List lista = query.list();
        session.flush();
        session.close();
        return lista;
    }
    
    public void remove(Curso curso) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
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
        Session session = NewHibernateUtil.getSessionFactory().openSession();
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

    public void incluirInscricao(Inscritoscurso inscritocurso){
        Transaction tx = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(inscritocurso);
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
    
    public Curso getCursoID(int idcurso) {
        Curso curso = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
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
