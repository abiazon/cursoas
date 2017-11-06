/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Cidade;
import modelo.Estado;
import modelo.Pessoa;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import util.NewHibernateUtil;

/**
 *
 * @author adriano
 */
public class PessoaDAO {
    
    public void addPessoa(Pessoa pessoa) {
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext rc = RequestContext.getCurrentInstance();

        Transaction tx = null;
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        tx = session.beginTransaction();
        try {
            session.save(pessoa);
            session.getTransaction().commit();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro Salvo com sucesso.",""));
        } catch (Exception e) {
            e.printStackTrace();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Falha ao Salvar Registro.",e.getMessage()));  
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
            String queryString = "from Pessoa where idpessoa = :idToFind";
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
    
    public Estado pegaestado(String ufestado){
        Estado estado;
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        String queryString = "from Estado where idunidFed = :idToFind ";
        Query query = session.createQuery(queryString);
        query.setString("idToFind", ufestado);
        estado = (Estado) query.uniqueResult();
        return estado;
    }

    public List<Pessoa> getPessoacpf(String cpf) {
        List listacpf=null;
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        try{
            String queryString = "from Pessoa where cpf = :cpfToFind";
            Query query = session.createQuery(queryString);
            query.setString("cpfToFind", cpf);
            listacpf = query.list(); 
            return listacpf;    
        }catch (RuntimeException e){
            e.printStackTrace();
        }finally{
            session.flush();
            session.close();
        }   
        return listacpf;
    }
    
    public List<Pessoa> getPessoaapelido(String apelido) {
        List listaapelido=null;
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        try{
            String queryString = "from Pessoa where apelido = :apelidoToFind";
            Query query = session.createQuery(queryString);
            query.setString("apelidoToFind", apelido);
            listaapelido = query.list(); 
            return listaapelido;    
        }catch (RuntimeException e){
            e.printStackTrace();
        }finally{
            session.flush();
            session.close();
        }   
        return listaapelido;
    }
}
