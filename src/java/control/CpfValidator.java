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

@FacesValidator("cpfValidator")
public class CpfValidator implements Validator {

    private final FacesMessage msg,msga;
    private PessoaDAO dao;
     private List listacpf;

    public CpfValidator() {
        msg = new FacesMessage("Validacao CPF falhou", "CPF Inválido");
        msga = new FacesMessage("Validacao CPF falhou", "CPF Já Cadastrado");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        msga.setSeverity(FacesMessage.SEVERITY_ERROR);
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent componente, Object valor) throws ValidatorException {
        String cpf = (String) valor;
//        cpf = cpf.replaceAll("\\.|-", "");
        
        if (cpf == null) {
            throw new ValidatorException(msg);
        } else {
            if (verificarSeDigIguais(cpf)) {
                throw new ValidatorException(msg);
            }
            String cpfGerado = "";

            cpfGerado = cpf.substring(0, 9);
            cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));
            cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));

            if (!cpfGerado.equals(cpf)) {
                throw new ValidatorException(msg);
            }
            dao = new PessoaDAO();
            listacpf = dao.getPessoacpf(cpf);
            if (listacpf.size()>0) {
                throw new ValidatorException(msga);
            }            
            System.out.println("getasvalidatorcpf");
        }
    }
    
    private String calculoComCpf(String cpf) {
        int digGerado = 0;
        int mult = cpf.length() + 1;
        char[] charCpf = cpf.toCharArray();
        for (int i = 0; i < cpf.length(); i++) {
            digGerado += (charCpf[i] - 48) * mult--;
        }
        if (digGerado % 11 < 2) {
            digGerado = 0;
        } else {
            digGerado = 11 - digGerado % 11;
        }
        return String.valueOf(digGerado);
    }

    private boolean verificarSeDigIguais(String cpf) {
        if (cpf.equals("00000000000") || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999")
                || (cpf.length() != 11)) {
            return (true);
        }
        return (false);
    }
}
