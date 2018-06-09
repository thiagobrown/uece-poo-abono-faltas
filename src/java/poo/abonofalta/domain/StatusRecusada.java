package poo.abonofalta.domain;

public class StatusRecusada implements IStatus {

    @Override
    public void solicitar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação já encontra-se aprovada.");
    }

    @Override
    public void aprovar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação já encontra-se aprovada.");
    }

    @Override
    public void recusar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação já encontra-se aprovada.");
    }

    @Override
    public void retornar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação já encontra-se aprovada.");
    }

    @Override
    public void cancelar(Solicitacao solicitacao) {
        solicitacao.setStatus(StatusEnum.Cancelada);
    }

    @Override
    public void retornarAprovacao(Solicitacao solicitacao) {
        solicitacao.setStatus(StatusEnum.AguardandoRH);
    }
}
