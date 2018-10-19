package com.softwareag.linguist.config.dbmigrations;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.softwareag.linguist.domain.Authority;
import com.softwareag.linguist.security.AuthoritiesConstants;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog(order = "002")
public class LinguistSetupMigration {

    @ChangeSet(order = "01", author = "initiator", id = "01-addLinguistAuthorities")
    public void addLinguistAuthorities(MongoTemplate mongoTemplate) {
        Authority pmAuthority = new Authority();
        pmAuthority.setName(AuthoritiesConstants.PM);
        Authority translatorAuthority = new Authority();
        translatorAuthority.setName(AuthoritiesConstants.TRANSLATOR);
        Authority devAuthority = new Authority();
        devAuthority.setName(AuthoritiesConstants.DEVELOPER);
        mongoTemplate.save(pmAuthority);
        mongoTemplate.save(translatorAuthority);
        mongoTemplate.save(devAuthority);
    }


}
