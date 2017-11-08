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
@FacesValidator("apelidoValidator")
public class ApelidoValidator implements Validator{
     private final FacesMessage msg;
     private PessoaDAO dao;
     private static String cpfaq;

    public ApelidoValidator() {
        msg = new FacesMessage("Erro", "Apelido já Utilizado");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
    }
    
    @Override
    public void validate(FacesContext facesContext, UIComponent componente, Object valor) throws ValidatorException {
        String apelido = (String) valor;
        if (apelido == null) {
            throw new ValidatorException(msg);
        } else {
            dao = new PessoaDAO();
            Pessoa pessoa;
            pessoa = dao.getPessoaapelido(apelido);
            String cpf = pessoa.getCpf();
          
            cpfaq = CntrPessoa.getCpfcontrole();

        }
    }  
}
