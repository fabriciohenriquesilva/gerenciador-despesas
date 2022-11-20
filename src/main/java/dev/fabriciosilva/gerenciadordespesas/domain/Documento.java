package dev.fabriciosilva.gerenciadordespesas.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Documento {

    public enum TipoDocumento {
        CPF {
            public String getMascara(){
                return "###.###.###-##";
            }
        },
        CNPJ {
            public String getMascara(){
                return "##.###.###/####-##";
            }
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDocumento tipoDocumento;
    @Column(nullable = false)
    private String codigo;

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
