/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.PessoaDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Pessoa;



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

    public void testausuario(){
        dao = new PessoaDAO();
        List<Pessoa> lista;
        lista = dao.testausuario(usuario,senha);
        if (lista.size()==1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Logado", null)); 
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);				
            httpSession.setAttribute("autenticado", true);
            httpSession.setAttribute("usuario", usuario); 
            httpSession.setAttribute("idusuario", lista.get(0).getId());
            System.out.println("PAGINA:"+this.getPagina());
            if ("infocurso.xhtml".equals(this.getPagina())){
                System.out.println("tentando");
                CntrCurso cc = new CntrCurso();
                cc.inscreverCurso();
            }
            //return this.getPagina();     
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(this.getPagina());
            } catch (IOException ex) {
                Logger.getLogger(cntrUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
       } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Apelido ou Senha inválidos", null)); 
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);				
            httpSession.setAttribute("autenticado", false);				
            System.out.println("atributo NAO OK");
//            return "";
       }
    }
    
    public String sair() {
        System.out.println("SAINDO");
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        httpSession.removeAttribute("autenticado");
        httpSession.removeAttribute("usuario");
        httpSession.removeAttribute("idusuario");
        httpSession.invalidate(); 
        return "login.xhtml";
    }    
   
}
