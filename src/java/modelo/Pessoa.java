package modelo;
// Generated 25/10/2017 08:09:13 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Pessoa generated by hbm2java
 */
public class Pessoa  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa_id")
     private int id;
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
     private String celular;

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Pessoa() {
    }
    
    public Pessoa(String nome, String endereco, String cidade, String estado, String telefone, String bairro, Date datanascimento, String email, Integer numeroresidencia, String cep, String cpf, Character sexo, String apelido, String celular) {
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.bairro = bairro;
        this.datanascimento = datanascimento;
        this.email = email;
        this.numeroresidencia = numeroresidencia;
        this.cep = cep;
        this.cpf = cpf;
        this.sexo = sexo;
        this.apelido = apelido;
        this.celular = celular;
    }
	
    public Pessoa(int id) {
        this.id = id;
    }
    public Pessoa(int id, String nome, String endereco, String cidade, String estado, String telefone, String bairro, Character tipopessoa, Date datanascimento, String email, Integer numeroresidencia, String cep, String cpf, Character sexo, String senha, String apelido) {
       this.id = id;
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
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return this.endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getCidade() {
        return this.cidade;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getTelefone() {
        return this.telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getBairro() {
        return this.bairro;
    }
    
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public Character getTipopessoa() {
        return this.tipopessoa;
    }
    
    public void setTipopessoa(Character tipopessoa) {
        this.tipopessoa = tipopessoa;
    }
    public Date getDatanascimento() {
        return this.datanascimento;
    }
    
    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getNumeroresidencia() {
        return this.numeroresidencia;
    }
    
    public void setNumeroresidencia(Integer numeroresidencia) {
        this.numeroresidencia = numeroresidencia;
    }
    public String getCep() {
        return this.cep;
    }
    
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getCpf() {
        return this.cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public Character getSexo() {
        return this.sexo;
    }
    
    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }
    public String getSenha() {
        return this.senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getApelido() {
        return this.apelido;
    }
    
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }




}


