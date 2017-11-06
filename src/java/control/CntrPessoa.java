package control;

import dao.PessoaDAO;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import modelo.Cidade;
import modelo.Estado;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import modelo.Pessoa;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
/**
 *
 * @author adriano
 */
public class CntrPessoa {
    private int qtdpessoas;
    private Pessoa pessoa;
    private String nomeestado;
    private PessoaDAO dao;
    private DataModel<Pessoa> listaPessoa;
    List<Pessoa> lista;
    private List<Cidade> listacidade;
    private List<Estado> listestados;

    @PostConstruct
    public void init() {
        listestados = new PessoaDAO().listEstado();
    }
    
    public void comboEstadoChange() {
        listacidade = new PessoaDAO().listCidade(pessoa.getEstado());
    }
    
    public void comboEstadoChangeDialog() {
        pessoa.setCidade("");
        listacidade = new PessoaDAO().listCidade(pessoa.getEstado());
    }    
    
    public void setListacidade(List<Cidade> listacidade) {
        this.listacidade = listacidade;
    }

    public List<Cidade> getListacidade() {
        return listacidade;
    }

    public void setListestados(List<Estado> listestados) {
        this.listestados = listestados;
    }
    
    public List<Estado> getListestados() {
        return listestados;
    }
    
    public String getNomeestado() {
        return nomeestado;
    }

    public void setNomeestado(String nomeestado) {
        this.nomeestado = nomeestado;
    }

    public CntrPessoa() {
        pessoa = new Pessoa();
        listestados = new PessoaDAO().listEstado();
    }

    public CntrPessoa(int id, int qtdpessoas, String nome, String endereco, String cidade, String estado, String telefone, String bairro, Character tipopessoa, Date datanascimento, String email, Integer numeroresidencia, String cep, String cpf, Character sexo, String senha, String apelido) {
    }

    public int getQtdpessoas() {
        return qtdpessoas;
    }

    public void setQtdpessoas(int qtdpessoas) {
        this.qtdpessoas = qtdpessoas;
    }

    public void prepararalterarPessoa() {
        pessoa = (Pessoa) (listaPessoa.getRowData());
        dao = new PessoaDAO();
        listacidade = new PessoaDAO().listCidade(pessoa.getEstado());
        nomeestado = dao.pegaestado(pessoa.getEstado()).getnomeestado();
        System.out.println("pessoa posicionada:"+pessoa.getNome()+" "+pessoa.getEmail()+pessoa.getEstado()+" "+nomeestado);
    }

    public void alterarPessoa() {
        System.out.println("tentando salvar pessoa");
        dao = new PessoaDAO();
        dao.update(pessoa);
        System.out.println("Salvando-->"+pessoa.getEstado()+" "+pessoa.getCidade());
//            rc.execute("PF('pessoaDialog').hide()");
        
    }

    public void addpessoa() {
        pessoa.setTipopessoa('1');      
        dao = new PessoaDAO();
        dao.addPessoa(pessoa);
        pessoa = new Pessoa();
    }
    
    public DataModel getListarPessoa() {
        lista = new PessoaDAO().listPessoa();
        listaPessoa = new ListDataModel(lista);
        qtdpessoas = listaPessoa.getRowCount();
        return listaPessoa;
    }

    public String excluirPessoa() {
        Pessoa pessoaTemp = (Pessoa) (listaPessoa.getRowData());
        dao = new PessoaDAO();
        dao.remove(pessoaTemp);
        return "pessoa";
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public DataModel getListaPessoa() {
        return listaPessoa;
    }

    public void setListaPessoa(DataModel listaPessoa) {
        this.listaPessoa = listaPessoa;
    }

    public PessoaDAO getDao() {
        return dao;
    }

    public void setDao(PessoaDAO dao) {
        this.dao = dao;
    }
}
