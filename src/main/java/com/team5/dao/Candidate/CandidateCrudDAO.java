package com.team5.dao.Candidate;

import com.team5.dao.interfaces.CrudDao;
import com.team5.models.Candidate;
import com.team5.models.CandidateFilter;
import java.util.List;
public interface CandidateCrudDAO extends CrudDao<Candidate> {
    List<Candidate> getEntitiesByPage(CandidateFilter filter, int offset, int total);
    List<Candidate> getSortedEntitiesByPage(CandidateFilter filter, String sortBy, int pageId, int total);
}