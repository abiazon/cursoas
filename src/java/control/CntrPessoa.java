package control;

import dao.PessoaDAO;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
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
    private int id, qtdpessoas;
    private String nome;
    private String endereco;
    private String cidade;
    private String estado;
    private String telefone;
    private String bairro;
    private Character tipopessoa;
    private Date datanascimento;
    private String email;
    private Integer numeroresidencia;
    private String cep;
    private String cpf;
    private Character sexo;
    private String senha;
    private String apelido;
    private Pessoa pessoa;
    private PessoaDAO dao;
    private DataModel<Pessoa> listaPessoa;
    List<Pessoa> lista;
    private List<Cidade> listacidade;
    private List<Estado> listestados;

    @PostConstruct
    public void init() {
        listestados = new PessoaDAO().listEstado();
    }
    
    public void register() {
        // Just for debug. Don't do this in real! Hash the password, save to DB and forget it ;)
        System.out.println("Username: " + nome);
        System.out.println("Password: " + apelido);

        // Show succes message.
        FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Succes!"));
    }
        
    public void comboEstadoChange() {
        listacidade = new PessoaDAO().listCidade(getEstado());
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

    public CntrPessoa() {
//        listestados = new PessoaDAO().listEstado();
    }

    public CntrPessoa(int id, int qtdpessoas, String nome, String endereco, String cidade, String estado, String telefone, String bairro, Character tipopessoa, Date datanascimento, String email, Integer numeroresidencia, String cep, String cpf, Character sexo, String senha, String apelido) {
        this.id = id;
        this.qtdpessoas = qtdpessoas;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.bairro = bairro;
        this.tipopessoa = tipopessoa;
        this.datanascimento = datanascimento;
        this.email = email;
        this.numeroresidencia = numeroresidencia;
        this.cep = cep;
        this.cpf = cpf;
        this.sexo = sexo;
        this.senha = senha;
        this.apelido = apelido;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public int getQtdpessoas() {
        return qtdpessoas;
    }

    public void setQtdpessoas(int qtdpessoas) {
        this.qtdpessoas = qtdpessoas;
    }

    public void prepararalterarPessoa() {
        pessoa = (Pessoa) (listaPessoa.getRowData());
        System.out.println(pessoa.getNome()+" "+pessoa.getEmail());
    }

    public void alterarPessoa() {
        dao = new PessoaDAO();
        dao.update(pessoa);
    }

    public void addpessoa() {
        pessoa = new Pessoa(getNome(), getEndereco(),getCidade(), getEstado(), getTelefone(), getBairro(), getDatanascimento(), getEmail(), getNumeroresidencia(), getCep(), getCpf(), getSexo(), getApelido() );
        pessoa.setTipopessoa('1');      
        dao = new PessoaDAO();
        dao.addPessoa(pessoa);
        pessoa = new Pessoa();
        dao = new PessoaDAO();
    }
    
    public String verificaCpfExistente(){
         return cpf;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Character getTipopessoa() {
        return tipopessoa;
    }

    public void setTipopessoa(Character tipopessoa) {
        this.tipopessoa = tipopessoa;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumeroresidencia() {
        return numeroresidencia;
    }

    public void setNumeroresidencia(Integer numeroresidencia) {
        this.numeroresidencia = numeroresidencia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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
