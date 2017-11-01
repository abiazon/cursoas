/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.CursoDAO;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import modelo.Curso;

@ManagedBean    
@SessionScoped
/**
 *
 * @author bruno
 */
public class CntrCurso {
    private int id,qtdcursos;
    private String nome;
    private Date datainicio, datafim;
    private Double valorcurso; 
    private DataModel<Curso> listarCurso;
    private Curso curso;
    private CursoDAO dao;
    List<Curso> lista;

    public CntrCurso() {
//        valorcurso = 0d;
    }

    public CntrCurso(int id, String nome, Date datainicio, Double valorcurso, Date datafim) {
        this.id = id;
        this.nome = nome;
        this.datainicio = datainicio;
        this.valorcurso = valorcurso;
        this.datafim = datafim;
    }

    public DataModel getListarCurso() {
        lista = new CursoDAO().listCurso();
        listarCurso = new ListDataModel(lista); 
        qtdcursos=listarCurso.getRowCount();
        return listarCurso;
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

    public String excluirCurso() {
        Curso cursoTemp = (Curso) (listarCurso.getRowData());
        dao = new CursoDAO();
        dao.remove(cursoTemp);
        return "curso";
    }
    
    public void prepararalterarCurso() {
        curso = (Curso) (listarCurso.getRowData());
    }
    
    public void alterarCurso(){
        dao = new CursoDAO();
        dao.update(curso);
    }
    
    public void addarraycurso() {
    }

    public void addcursco() {
       curso = new Curso(getNome(), getDatainicio(), getValorcurso(), getDatafim());
       dao = new CursoDAO();
       dao.addCurso(curso);
       nome="";
       datainicio=null;
       datafim=null;
       valorcurso=null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Double getValorcurso() {
        return valorcurso;
    }

    public void setValorcurso(Double valorcurso) {
        this.valorcurso = valorcurso;
    }

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }
}
