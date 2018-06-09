package poo.abonofalta.domain;

import static entities.RepositoryAtomic.save;
import entities.annotations.ActionDescriptor;
import entities.annotations.Editor;
import entities.annotations.EntityDescriptor;
import entities.annotations.ParameterDescriptor;
import entities.annotations.PropertyDescriptor;
import entities.annotations.View;
import entities.annotations.Views;
import entities.descriptor.PropertyType;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@EntityDescriptor //(template = "@TABLE_CRUDE")
@Entity
@Views({
    @View(title = "Nova Solicitação",
            name = "NovaSolicitacao",
            members = "[codigo;"
            + " #funcionario;"
            + " #inicio;"
            + " #termino;"
            + " #motivo;"
            + " #tipoSolicitacao;"
            + " *status];"
            + " [solicitar()]",
            namedQuery = "Select new poo.abonofalta.domain.Solicitacao()"),
    @View(name = "AprovacaoDaChefia",
            title = "Analisar Solicitações",
            namedQuery = "SolicitacoesAguardandoChefia",
            members = "[*codigo;*funcionario;*inicio;*termino;*motivo;#observacao];[aprovar();recusar();cancelar()]",
            template = "@PAGER"),
    @View(name = "AbonarFaltas",
            title = "Abonar Faltas",
            namedQuery = "SolicitacoesAguardandoRH",
            members = "[*codigo;*funcionario;*inicio;*termino;#observacao];[aprovar();recusar();retornar();cancelar()]",
            template = "@PAGER"),
    @View(name = "RetornarAprovacao",
            title = "Retornar Aprovação",
            namedQuery = "SolicitacoesAprovadas",
            members = "[*codigo;*funcionario;*inicio;*termino;*observacao];[retornarAprovacao()]",
            template = "@PAGER")
})
@NamedQueries({
    @NamedQuery(
            name = "SolicitacoesAguardandoChefia",
            query = "Select s  From Solicitacao s Where s.status = poo.abonofalta.domain.StatusEnum.AguardandoChefia")
    ,     
    @NamedQuery(
            name = "SolicitacoesAguardandoRH",
            query = "Select s  From Solicitacao s Where s.status = poo.abonofalta.domain.StatusEnum.AguardandoRH")
    ,
    @NamedQuery(
            name = "SolicitacoesAprovadas",
            query = "Select s  From Solicitacao s Where s.status = poo.abonofalta.domain.StatusEnum.Aprovada")
})
public class Solicitacao implements Serializable {

    @Id
    @GeneratedValue
    @Column(length = 10)
    @PropertyDescriptor(displayName = "Nº Solicitacao")
    private Long codigo;

    @ManyToOne
    @NotNull(message = "Funcionário obrigatório!")
    private Funcionario funcionario;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull(message = "Data inicio obrigatório!")
    private Date inicio;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull(message = "Data termino obrigatório!")
    private Date termino;

    @Lob
    @NotBlank(message = "Motivo inválido!")
    private String motivo;

    @Lob
    private String observacao;

    @Enumerated(EnumType.ORDINAL)
    @Column(updatable = false)
    @NotNull(message = "Tipo da Solicitação obrigatório!")
    private TipoSolicitacaoEnum tipoSolicitacao;

    //@ManyToOne
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status = StatusEnum.NovaSolicitacao;

    @Version
    private Timestamp version;

    public Solicitacao() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long matricula) {
        this.codigo = matricula;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getTermino() {
        return termino;
    }

    public void setTermino(Date termino) {
        this.termino = termino;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    protected void setStatus(StatusEnum status) {
        this.status = status;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public TipoSolicitacaoEnum getTipoSolicitacao() {
        return tipoSolicitacao;
    }

    public void setTipoSolicitacao(TipoSolicitacaoEnum tipoSolicitacao) {
        this.tipoSolicitacao = tipoSolicitacao;
    }

    @ActionDescriptor(refreshView = true, displayName = "Solicitar", shortDescription = "Solicitar")
    public String solicitar() {
        this.status.solicitar(this);
        save(this);

        return "Solicitação gravada com sucesso!";
    }

    @ActionDescriptor(confirm = true, refreshView = true, displayName = "Aprovar", shortDescription = "Aprovar")
    public String aprovar() {
        this.status.aprovar(this);
        save(this);

        return "Solicitação aprovada!";
    }

    @ActionDescriptor(confirm = true, refreshView = true, displayName = "Recusar", shortDescription = "Recusar")
    public String recusar() {
        this.status.recusar(this);
        save(this);

        return "Solicitação recusada!";
    }

    @ActionDescriptor(confirm = true, refreshView = true, displayName = "Retornar", shortDescription = "Retornar")
    public String retornar(
            @Editor(propertyType = PropertyType.MEMO)
            @ParameterDescriptor(displayName = "Motivo") String motivo) {
        
        if (motivo == null || motivo.isEmpty()) {
            throw new IllegalArgumentException("Motivo inálido");
        }

        this.status.retornar(this);
        this.motivo = motivo;
        save(this);

        return "Solicitação retornada!";
    }

    @ActionDescriptor(confirm = true, refreshView = true, displayName = "Cancelar Solicitacao", shortDescription = "Cancelar Solicitacao")
    public String cancelar() {
        this.status.cancelar(this);
        save(this);

        return "Solicitação cancelada!";
    }

    @ActionDescriptor(confirm = true, refreshView = true, displayName = "Retornar Aprovacao", shortDescription = "Retornar")
    public String retornarAprovacao() {
        this.status.retornarAprovacao(this);
        save(this);

        return "Solicitação retornada!";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.codigo);
        hash = 19 * hash + Objects.hashCode(this.funcionario);
        hash = 19 * hash + Objects.hashCode(this.inicio);
        hash = 19 * hash + Objects.hashCode(this.termino);
        hash = 19 * hash + Objects.hashCode(this.status);
        hash = 19 * hash + Objects.hashCode(this.tipoSolicitacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Solicitacao other = (Solicitacao) obj;
        if (this.codigo != other.codigo && (this.codigo == null || !this.codigo.equals(other.codigo))) {
            return false;
        }
        if (this.funcionario != other.funcionario && (this.funcionario == null || !this.funcionario.equals(other.funcionario))) {
            return false;
        }
        if (this.inicio != other.inicio && (this.inicio == null || !this.inicio.equals(other.inicio))) {
            return false;
        }
        if (this.termino != other.termino && (this.termino == null || !this.termino.equals(other.termino))) {
            return false;
        }
        if (this.tipoSolicitacao != other.tipoSolicitacao) {
            return false;
        }
        if (this.status != other.status && (this.status == null || !this.status.equals(other.status))) {
            return false;
        }
        return true;
    }
}
