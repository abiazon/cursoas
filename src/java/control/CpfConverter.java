/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cpfConverter")
public class CpfConverter implements Converter {
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
            String semPontuacao = valor.replaceAll("\\.|-", ""); //removendo pontos e traços
            return semPontuacao;
        }
   
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
            String cpf = (String) objeto;
            cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
            return cpf;
        }
}