package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Transactional
public class SequenceGeneratorService {

	@Autowired
	private MongoOperations mongoOperations;
	
	private static final Logger log = LoggerFactory.getLogger(AppController.class);
	
	public long generateSequence(String seqName) {
		
	    DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
	      new Update().inc("seq",1), options().returnNew(true).upsert(true),
	      DatabaseSequence.class);
	    log.info("Generating Sequence No: "+(!Objects.isNull(counter) ? counter.getSeq() : 1));
	    return !Objects.isNull(counter) ? counter.getSeq() : 1;
	  
	}
}
