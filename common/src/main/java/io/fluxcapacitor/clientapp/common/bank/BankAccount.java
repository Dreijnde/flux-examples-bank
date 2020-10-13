package io.fluxcapacitor.clientapp.common.bank;

import io.fluxcapacitor.javaclient.FluxCapacitor;
import io.fluxcapacitor.javaclient.persisting.eventsourcing.EventSourced;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@EventSourced
@Value
@Builder(toBuilder = true)
public class BankAccount {
    @NotBlank String accountId, userId;

    @Builder.Default
    @NotNull @PositiveOrZero BigDecimal maxOverdraft = BigDecimal.ZERO;

    @Builder.Default
    @NotNull BigDecimal balance = BigDecimal.ZERO;

    boolean closed;

    public static BankAccount load(String accountId) {
        return FluxCapacitor.loadAggregate(accountId, BankAccount.class).get();
    }
}