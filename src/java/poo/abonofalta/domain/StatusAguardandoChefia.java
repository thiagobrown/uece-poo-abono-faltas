package poo.abonofalta.domain;
//@Entity
public class StatusAguardandoChefia implements IStatus {

    @Override
    public void solicitar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação está aguardando aprovação da Chefia");
    }

    @Override
    public void aprovar(Solicitacao solicitacao) {
        solicitacao.setStatus(StatusEnum.AguardandoRH);
    }

    @Override
    public void recusar(Solicitacao solicitacao) {
        solicitacao.setStatus(StatusEnum.Recusada);
    }

    @Override
    public void retornar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação não pode ser retornada.");
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
