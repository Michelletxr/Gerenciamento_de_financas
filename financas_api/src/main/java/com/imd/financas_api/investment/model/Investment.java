package com.imd.financas_api.investment.model;

import com.imd.financas_api.user.model.User;
import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name="investiment", schema = "public")
public class Investment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private String corretora;

    private String titulo;

    private String tipo_titulo;

    private Date vencimento_titulo;

    private Date data_investimento;

    private Double investido;

    private String objetivo;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UUID user;

    @Builder
    public Investment(UUID id, Integer id_investment, String corretora, String titulo, String tipo_titulo,
                      Date vencimento_titulo, Date data_investimento, Double investido, String objetivo, UUID user) {
        this.id = id;
        this.corretora = corretora;
        this.titulo = titulo;
        this.tipo_titulo = tipo_titulo;
        this.vencimento_titulo = vencimento_titulo;
        this.data_investimento = data_investimento;
        this.investido = investido;
        this.objetivo = objetivo;
        this.user = user;
    }
}
