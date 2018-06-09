package poo.abonofalta.domain;

public class StatusNovaSolicitacao implements IStatus {

    @Override
    public void solicitar(Solicitacao solicitacao) {
        solicitacao.setStatus(solicitacao.getTipoSolicitacao().getStatus());
    }

    @Override
    public void aprovar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação ainda não foi solicitada pelo Funcionario.");
    }

    @Override
    public void recusar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação ainda não foi solicitada pelo Funcionario.");
    }

    @Override
    public void retornar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação ainda não foi solicitada pelo Funcionario.");
    }

    @Override
    public void cancelar(Solicitacao solicitacao) {
        throw new IllegalStateException("A solicitação ainda não foi solicitada pelo Funcionario.");
    }

    @Override
    public void retornarAprovacao(Solicitacao solicitacao) {
        throw new IllegalStateException("É permitido estornar apenas solicitações já aprovadas pelo RH.");
    }    
}
