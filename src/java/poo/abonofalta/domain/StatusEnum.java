package poo.abonofalta.domain;

public enum StatusEnum implements IStatus {
    
    NovaSolicitacao(new StatusNovaSolicitacao()),
    AguardandoChefia(new StatusAguardandoChefia()),
    AguardandoRH(new StatusAguardandoRH()),
    Aprovada(new StatusAprovada()),
    Recusada(new StatusRecusada()),
    Cancelada(new StatusCancelada());
    
    private IStatus status;
    
    StatusEnum(IStatus status) {
        this.status = status;
    }

    @Override
    public void solicitar(Solicitacao solicitacao) {
        status.solicitar(solicitacao);
    }

    @Override
    public void aprovar(Solicitacao solicitacao) {
        status.aprovar(solicitacao);
    }

    @Override
    public void recusar(Solicitacao solicitacao) {
        status.recusar(solicitacao);
    }

    @Override
    public void retornar(Solicitacao solicitacao) {
        status.retornar(solicitacao);
    }

    @Override
    public void cancelar(Solicitacao solicitacao) {
        status.cancelar(solicitacao);
    }

    @Override
    public void retornarAprovacao(Solicitacao solicitacao) {
        status.retornarAprovacao(solicitacao);
    }   
}
