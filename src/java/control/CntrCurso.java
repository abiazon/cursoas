/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.CursoDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private int qtdcursos;
    //private String nome;
    //private Date datainicio, datafim;
    //private Double valorcurso; 
    private DataModel<Curso> listarCurso;
    private Curso curso;
    private CursoDAO dao;
    List<Curso> lista;
    List maphora,mapminuto;

    public CntrCurso() {
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

    public DataModel getListarCurso() {
        lista = new CursoDAO().listCurso();
        listarCurso = new ListDataModel(lista); 
        qtdcursos=listarCurso.getRowCount();
        return listarCurso;
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

    public void addcursco() {
       dao = new CursoDAO();
       dao.addCurso(curso);
       curso = new Curso();
    }

}
