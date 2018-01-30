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
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import modelo.Pessoa;

/**
 *
 * @author bruno
 */
@FacesValidator("apelidoValidator")
public class ApelidoValidator implements Validator{
     private final FacesMessage msg;
     private PessoaDAO dao;
     private List<Pessoa> listaapelido;
     FacesContext context = FacesContext.getCurrentInstance();

    public ApelidoValidator() {
        msg = null;
//        msg = new FacesMessage("Erro", "Apelido já Utilizado");
//        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
    }
    
    @Override
    public void validate(FacesContext facesContext, UIComponent componente, Object valor) throws ValidatorException {
        String apelido = (String) valor;
        if (apelido == null || "".equals(apelido.trim())) {
            throw new ValidatorException(new FacesMessage("Apelido é Requerido"));
        } else {
            dao = new PessoaDAO();
            listaapelido = dao.getPessoaapelido(apelido);
            if (listaapelido.size()>1){
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Apelido já Utilizado", null));
            } else if (listaapelido.size()==1) {
                String cpfatual = (String) componente.getAttributes().get("cpf"); 
                System.out.println("teste:"+cpfatual);
                if (!cpfatual.equals("")){
                    String cpf = listaapelido.get(0).getCpf();
                    if (!cpf.equals(cpfatual)){
                        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Apelido já Utilizado", null));
                    }
                } else {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Apelido já Utilizado", null));
                }
            }
        }
    }  
}
