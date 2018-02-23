/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.CursoDAO;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Curso;
import modelo.Inscritoscurso;

@ManagedBean
@SessionScoped
/**
 *
 * @author bruno
 */
public class CntrCurso implements Serializable {
    private int qtdcursos;
    //private String nome;
    //private Date datainicio, datafim;
    //private Double valorcurso; 
    private DataModel<Curso> listarCurso;
    private Curso curso;
    private Inscritoscurso inscritocurso;
    private CursoDAO dao = new CursoDAO();
    public List<Curso> lista;
    public List maphora,mapminuto;
    public Date guarda;
    public int idaluno;
    private Boolean logado;

    public Boolean getLogado() {
        return logado;
    }

    public void setLogado(Boolean logado) {
        this.logado = logado;
    }

    public Date getGuarda() {
        return guarda;
    }

    public void setGuarda(Date guarda) {
        this.guarda = guarda;
    }

    public CntrCurso() {
        System.out.println("estou aqui");
        curso = new Curso();
        listahora();
        listaminuto();
//        valorcurso = 0d;
    }

    public CntrCurso(int id, String nome, Date datainicio, Double valorcurso, Date datafim) {
//        this.id = id;
//        this.nome = nome;
//        this.datainicio = datainicio;
//        this.valorcurso = valorcurso;
//        this.datafim = datafim;
    }

//    public DataModel getListarCurso() {
//        lista = new CursoDAO().listCurso();
//        listarCurso = new ListDataModel(lista); 
//        qtdcursos=listarCurso.getRowCount();
//        return listarCurso;
//    }

    public List<Curso> getListarCurso() {
        lista = new CursoDAO().listCurso();
        qtdcursos = lista.size();
        System.out.println("listando Cursos na datatable");
        return lista;
    }
    
    public List<Curso> getListarCursoVigente() {
        lista = new CursoDAO().listCursoVigentes();
        qtdcursos = lista.size();
        System.out.println("listando Cursos na datatable");
        return lista;
    }
    
    public final void listahora(){
        maphora= new ArrayList();
        for (int i=0; i<23;i++)  {
          maphora.add(i);
        }
    }
    
    public final void listaminuto(){
        mapminuto = new ArrayList();
        for (int i=0; i<59;i++)  {
          mapminuto.add(i);
        }
    }
    
    public List getMaphora() {
        return maphora;
    }

    public void setMaphora(List maphora) {
        this.maphora = maphora;
    }

    public List getMapminuto() {
        return mapminuto;
    }

    public void setMapminuto(List mapminuto) {
        this.mapminuto = mapminuto;
    }
    
    public int getQtdcursos() {
        return qtdcursos;
    }

    public void setQtdcursos(int qtdcursos) {
        this.qtdcursos = qtdcursos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String excluirCurso(Curso curso) {
//        Curso cursoTemp = (Curso) (listarCurso.getRowData());
//        dao = new CursoDAO();
        dao.remove(curso);
        return "curso";
    }
    
    public void testalogado(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Boolean conectado = (Boolean) session.getAttribute("autenticado");			
        cntrUsuario.pagina= FacesContext.getCurrentInstance().getViewRoot().getViewId().replace("/","");
        System.out.println("TESTALOGADO");            
        //invertido
        if (conectado == null || !conectado) {
            logado = true;
        } else {logado = false;}
    }
    
    public String inscreverCurso(){
        System.out.println("tentando salvar registro:"+this.getCurso().getId());
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        idaluno = (int) session.getAttribute("idusuario");
        inscritocurso = new Inscritoscurso();
        inscritocurso.setIdaluno(idaluno);
        inscritocurso.setIdcurso(this.getCurso().getId());
        dao.incluirInscricao(inscritocurso);
        return "pag-sucesso";
    }
    
    public void prepararalterarCurso() {
        curso = (Curso) (listarCurso.getRowData());
    }
    
    public void alterarCurso(){
//        dao = new CursoDAO();
        dao.update(curso);
    }

    public void addcurso() {
       guarda = curso.getDatafimmatricula();
       System.out.println("HORARIO:"+curso.getHorario());
//       dao = new CursoDAO();
       dao.addCurso(curso);
       curso = new Curso();
    }
    
    public void informacoescurso(){
        try {  
            FacesContext.getCurrentInstance().getExternalContext().redirect("infocurso.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CntrCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
// nesse exemplo eu pego toda a url da pagina atual
//            HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//            System.out.println("Origem:"+origRequest.getRequestURL()+" Pagina:"+cntrUsuario.pagina);
