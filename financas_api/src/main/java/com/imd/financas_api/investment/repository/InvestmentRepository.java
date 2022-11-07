package com.imd.financas_api.investment.repository;

import com.imd.financas_api.investment.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface InvestmentRepository extends JpaRepository<Investment, UUID>{
   // public Investment findByLogin(UUID id);
}
