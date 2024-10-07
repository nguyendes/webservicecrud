package org.example.repository;

import org.example.entity.Ctsp;
import org.example.entity.CtspDetail;
import org.example.entity.CtspUpdateInfo;

import java.util.List;

public interface CtspRepository {
    List<CtspDetail> getAllCtspDetails();
    CtspDetail getCtspById(int idSp);
    boolean addCtsp(Ctsp ctsp);
    boolean updateCtsp(List<CtspUpdateInfo> updates);
    boolean deleteCtsp(int id);
}

