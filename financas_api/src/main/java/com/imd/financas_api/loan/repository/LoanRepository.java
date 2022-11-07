package com.imd.financas_api.loan.repository;

import com.imd.financas_api.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface LoanRepository extends JpaRepository<Loan, UUID>{
    //public Loan findByLogin(UUID id);
}
