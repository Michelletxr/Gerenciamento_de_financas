package com.imd.financas_api.loan.repository;

import com.imd.financas_api.loan.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParcelRepository extends JpaRepository<Parcel, UUID>  {
}


