package control;

import dao.PessoaDAO;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import modelo.Cidade;
import modelo.Estado;
import javax.annotation.PostConstruct;
import modelo.Pessoa;


@ManagedBean
@SessionScoped
/**
 *
 * @author adriano
 */
public class CntrPessoa {
    private int qtdpessoas;
    public Pessoa pessoa,pessoa1;
    private String nomeestado,confirmasenha;
    private PessoaDAO dao;
    private DataModel<Pessoa> listaPessoa;
    List<Pessoa> lista;
    private List<Cidade> listacidade;
    private List<Estado> listestados;

    @PostConstruct
    public void init() {
        System.out.println("postconstruct");
        listestados = new PessoaDAO().listEstado();
    }
    
    public CntrPessoa() {
        System.out.println("instanciando pessoa");
        pessoa = new Pessoa();
//        listestados = new PessoaDAO().listEstado();
    }

    public CntrPessoa(int id, int qtdpessoas, String nome, String endereco, String cidade, String estado, String telefone, String bairro, Character tipopessoa, Date datanascimento, String email, Integer numeroresidencia, String cep, String cpf, Character sexo, String senha, String apelido) {
    }
    
    public List<Pessoa> getListarPessoa() {
//        lista = new PessoaDAO().listPessoa();
//        listaPessoa = new ListDataModel(lista);
//        pessoa = new Pessoa();
        lista = new PessoaDAO().listPessoa();
        qtdpessoas = lista.size();
        System.out.println("listando pessoas na datatable");
        return lista;
    }
    
    public void comboEstadoChange() {
        listacidade = new PessoaDAO().listCidade(pessoa.getEstado());
    }
    
    public void comboEstadoChangeDialog() {
        pessoa.setCidade("");
        listacidade = new PessoaDAO().listCidade(pessoa.getEstado());
    }  

    public void comboEstadoChangeDialog1() {
        pessoa1.setCidade("");
        listacidade = new PessoaDAO().listCidade(pessoa1.getEstado());
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
    public String getConfirmasenha() {
        return confirmasenha;
    }

    public void setConfirmasenha(String confirmasenha) {
        this.confirmasenha = confirmasenha;
    }
    
    public int getQtdpessoas() {
        return qtdpessoas;
    }

    public void setQtdpessoas(int qtdpessoas) {
        this.qtdpessoas = qtdpessoas;
    }

    public Pessoa getPessoa1() {
        return pessoa1;
    }

    public void setPessoa1(Pessoa pessoa1) {
        this.pessoa1 = pessoa1;
    }
    
    public void prepararalterarPessoa(Pessoa pessoa) {
//        pessoa1 = (Pessoa) (listaPessoa.getRowData());
        pessoa1 = pessoa;
        dao = new PessoaDAO();
        listacidade = dao.listCidade(pessoa1.getEstado());
        nomeestado = dao.pegaestado(pessoa1.getEstado()).getnomeestado();
        System.out.println("pessoa posicionada:"+pessoa1.getNome()+" "+pessoa1.getId());
    }

    public void alterarPessoa() {
        System.out.println("tentando salvar pessoa:"+pessoa1.getNome()+" Apelido:"+pessoa1.getApelido()+" ID:"+pessoa1.getId());
        dao = new PessoaDAO();
        //pessoa.setId(getId());
        dao.update(pessoa1);
        System.out.println("Salvando-->"+pessoa1.getEstado()+" "+pessoa1.getCidade());
//            rc.execute("PF('pessoaDialog').hide()");
        
    }

    public void limparCampos(){
        System.out.println("limpando");
      pessoa = new Pessoa();    
    }
    
    public void addpessoa() {
        pessoa.setTipopessoa('1');      
        dao = new PessoaDAO();
        dao.addPessoa(pessoa);
        pessoa = new Pessoa();
    }
    
    public String excluirPessoa(Pessoa pessoa) {
//        Pessoa pessoaTemp = (Pessoa) (listaPessoa.getRowData());
        dao = new PessoaDAO();
        dao.remove(pessoa);
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
