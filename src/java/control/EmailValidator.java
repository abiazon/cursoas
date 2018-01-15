/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.PessoaDAO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import modelo.Pessoa;

/**
 *
 * @author bruno
 */
@FacesValidator("emailValidator")
public class EmailValidator implements Validator{
    private PessoaDAO dao = new PessoaDAO();
    private List<Pessoa> listaemail;

    @Override
    public void validate(FacesContext facesContext, UIComponent componente, Object valor) throws ValidatorException {
        String email = (String) valor;
        dao = new PessoaDAO();
        listaemail = dao.getPessoaemail(email);
        if (listaemail.size()>1){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Email já Utilizado", null));
        } else if (listaemail.size()==1) {
            String cpf = listaemail.get(0).getCpf();
            String cpfatual = componente.getAttributes().get("cpf").toString();
            System.out.println("CPFE:"+cpfatual);
            if (!cpf.equals(cpfatual)){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email já Utilizado", null));
            }
        }
    }  
}
