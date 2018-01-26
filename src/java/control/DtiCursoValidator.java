/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author bruno
 */
@FacesValidator("dticursoValidator")
public class DtiCursoValidator implements Validator{
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat  formattera = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
    
    @Override
    public void validate(FacesContext facesContext, UIComponent componente, Object valor) throws ValidatorException {
        Date dataa = (Date) valor;

        String dataant = (String) componente.getAttributes().get("dtia");
        UIInput param1 = (UIInput) componente.findComponent("dtia");
//        System.out.println("DATA:"+data + " DATAANTES:"+dataant+ " outro:"+param1 );
System.out.println("data:"+dataa+" :"+dataant+" :"+param1);
    }
    
}
