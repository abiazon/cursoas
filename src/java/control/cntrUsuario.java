/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.PessoaDAO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import util.NewHibernateUtil;


/**
 *
 * @author bruno
 */
@ManagedBean
@ApplicationScoped
public class cntrUsuario {
    String usuario,senha;
    public static String pagina;

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }
    private PessoaDAO dao;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String testausuario(){
        dao = new PessoaDAO();
        if (dao.testausuario(usuario,senha).size()==1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Logado", null)); 
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);				
            httpSession.setAttribute("autenticado", true);
            httpSession.setAttribute("usuario", usuario); 
            System.out.println("PAGINA:"+this.getPagina());
            return(this.getPagina());
       } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Apelido ou Senha inválidos", null)); 
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);				
            httpSession.setAttribute("autenticado", false);				
            System.out.println("atributo NAO OK");
            return null;
       }
    }
    
    public String sair() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        httpSession.removeAttribute("autenticado");
        httpSession.removeAttribute("usuario");
        return "login";
    }    
}
