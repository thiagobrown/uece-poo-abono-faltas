package poo.abonofalta.domain;

public class StatusAguardandoRH implements IStatus {

    @Override
    public void solicitar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação está aguardando a aprovação do RH");
    }

    @Override
    public void aprovar(Solicitacao solicitacao) {
        solicitacao.setStatus(StatusEnum.Aprovada);
    }

    @Override
    public void recusar(Solicitacao solicitacao) {
        solicitacao.setStatus(StatusEnum.Recusada);
    }

    @Override
    public void retornar(Solicitacao solicitacao) {
        solicitacao.setStatus(StatusEnum.AguardandoChefia);
    }

    @Override
    public void cancelar(Solicitacao solicitacao) {
        solicitacao.setStatus(StatusEnum.Cancelada);
    }

    @Override
    public void retornarAprovacao(Solicitacao solicitacao) {
         throw new IllegalStateException("É permitido estornar apenas solicitações já aprovadas pelo RH.");
    }
}
