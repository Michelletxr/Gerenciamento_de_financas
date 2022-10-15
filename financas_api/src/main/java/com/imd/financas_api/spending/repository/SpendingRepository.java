package com.imd.financas_api.spending.repository;

import com.imd.financas_api.spending.model.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpendingRepository extends JpaRepository<Spending, UUID> {
}
