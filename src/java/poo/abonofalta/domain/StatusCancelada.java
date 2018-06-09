package poo.abonofalta.domain;

public class StatusCancelada implements IStatus {

    @Override
    public void solicitar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação já encontra-se cancelada!");
    }

    @Override
    public void aprovar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação já encontra-se cancelada!");
    }

    @Override
    public void recusar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação já encontra-se cancelada!");
    }

    @Override
    public void retornar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação já encontra-se cancelada!");
    }

    @Override
    public void cancelar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação já encontra-se cancelada!");
    }

    @Override
    public void retornarAprovacao(Solicitacao solicitacao) {
        throw new IllegalStateException("É permitido estornar apenas solicitações já aprovadas pelo RH.");
    }
}
