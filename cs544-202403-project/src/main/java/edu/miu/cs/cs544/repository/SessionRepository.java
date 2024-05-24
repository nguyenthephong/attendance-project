package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Session;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionRepository extends BaseRepository<Session,Long> {
}