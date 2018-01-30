/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
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
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
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
    
    public List testausuario(String apelido, String senha){
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        String queryString = "from Pessoa where apelido= :apelToFind and senha= :senhaToFind";
        Query query = session.createQuery(queryString);
        query.setString("apelToFind", apelido);
        query.setString("senhaToFind", senha);
        List checalogin = query.list();
        return checalogin;
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
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext rc = RequestContext.getCurrentInstance();

        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(pessoa);
            session.getTransaction().commit();
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro Salvo com sucesso.",""));
            rc.execute("PF('pessoaDialog').hide();");
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Falha ao Salvar Registro.",e.getMessage()));  
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.flush();
            session.close();
        }
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
        //Pessoa pessoa=null;
        List<Pessoa> listaapelido=null;
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        try{
            String queryString = "from Pessoa where apelido = :apelidoToFind";
            Query query = session.createQuery(queryString);
            query.setString("apelidoToFind", apelido);
            //pessoa = (Pessoa) query.uniqueResult();
            listaapelido = query.list(); 
        }catch (RuntimeException e){
            e.printStackTrace();
        }finally{
            session.flush();
            session.close();
        }   
        return listaapelido;
    }
    
    public List<Pessoa> getPessoaemail(String email) {
        List<Pessoa> listaemail=null;
        Session session = NewHibernateUtil.buildSessionFactory().openSession();
        try{
            String queryString = "from Pessoa where email = :emailToFind";
            Query query = session.createQuery(queryString);
            query.setString("emailToFind", email);
            listaemail = query.list(); 
        }catch (RuntimeException e){
            e.printStackTrace();
        }finally{
            session.flush();
            session.close();
        }   
        return listaemail;
    }

}
