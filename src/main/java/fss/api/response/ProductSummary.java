package fss.api.response;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 *
 * @author ehernandez
 */
@RegisterForReflection
public record ProductSummary(Integer id, String name, String clave, String unitSale, String category, String zone) {}
