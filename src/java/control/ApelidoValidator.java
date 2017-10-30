/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.PessoaDAO;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author bruno
 */
@FacesValidator("apelidoValidator")
public class ApelidoValidator implements Validator{
     private final FacesMessage msg;
     private PessoaDAO dao;

    public ApelidoValidator() {
        msg = new FacesMessage("Erro", "Apelido já Utilizado");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
    }
    
    @Override
    public void validate(FacesContext facesContext, UIComponent componente, Object valor) throws ValidatorException {
        String apelido = (String) valor;
        System.out.println("estou no validaapelido");
        if (apelido == null) {
            throw new ValidatorException(msg);
        } else {
            dao = new PessoaDAO();
            if (dao.getPessoaapelido(apelido)!= null) {
                throw new ValidatorException(msg);
            }
        }
    }  
}
