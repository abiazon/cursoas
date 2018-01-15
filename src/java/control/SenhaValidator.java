/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author bruno
 */
@FacesValidator("senhaValidator")
public class SenhaValidator implements Validator{
    FacesContext context = FacesContext.getCurrentInstance();    
    
    @Override
    public void validate(FacesContext facesContext, UIComponent componente, Object valor) throws ValidatorException {
        String teste = componente.getAttributes().get("passw").toString();
        String testa = (String) valor;
        System.out.println("AQUI"+teste+" --"+testa);
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
//        if(req.getParameter("passw") != null && !req.getParameter("passw").equals("")){
//            if(req.getParameter("passwc") != null && !req.getParameter("passwc").equals("")){
//                String senha = req.getParameter("passw");
//                String senhav = req.getParameter("passwc");
//                System.out.println("senhas:"+senha+"--"+senhav);
//                if (senha != senhav){throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas Não conferem", null));}
//            }
//        }
    }
}

