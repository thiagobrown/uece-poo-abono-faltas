package poo.abonofalta.domain;

public enum TipoSolicitacaoEnum {

    A_SERVICO {
        @Override
        protected StatusEnum getStatus() {
            return StatusEnum.AguardandoChefia;
        }
    },
    ATESTADO {
        @Override
        protected StatusEnum getStatus() {
            return StatusEnum.AguardandoRH;
        }
    },
    LICENCA {
        @Override
        protected StatusEnum getStatus() {
            return StatusEnum.AguardandoChefia;
        }
    },
    PROBLEMA_PONTO_ELETRONICO {
        @Override
        protected StatusEnum getStatus() {
            return StatusEnum.AguardandoRH;
        }
    },
    OUTROS {
        @Override
        protected StatusEnum getStatus() {
            return StatusEnum.AguardandoChefia;
        }
    };

    protected abstract StatusEnum getStatus();
}
