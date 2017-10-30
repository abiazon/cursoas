/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

@ManagedBean
/**
 *
 * @author adriano
 */
public class SelectOneMenuView {
    public String estado;
    public List<String> estados = new ArrayList<String>();
 
    
    @PostConstruct
    public void init() {
        
        estados.add("AC - Acre");
        estados.add("AL - Alagoas");
        estados.add("AP -Amapá");
        estados.add("AM - Amazonas");
        estados.add("BA - Bahia");
        estados.add("CE - Ceará");
        estados.add("DF - Distrito Federa");
        estados.add("ES - Espírito Santo");
        estados.add("GO - Goiás");
        estados.add("MA - Maranhão");
        estados.add("MT - Mato Grosso");
        estados.add("MS - Mato Grosso do Sul");
        estados.add("MG - Pará");
        estados.add("PB - Paraíba");
        estados.add("PE - Pernambuco");
        estados.add("PI - Piauí");
        estados.add("PR - Paraná");
        estados.add("RJ - Rio de Janeiro");
        estados.add("RN - Rio Grande do Norte");
        estados.add("RS - Rio Grande do Sul");
        estados.add("RO - Rondônia");
        estados.add("RR - Roraima");
        estados.add("SC - Santa Catarina");
         estados.add("SP - São Paulo");
        estados.add("SE - Sergipe");
        estados.add("TO - Tocantins");
        
    
    
    }

    public List<String> getEstados() {
        return estados;
    }

    public void setEstados(List<String> estados) {
        this.estados = estados;
    }
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
