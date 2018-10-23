package com.softwareag.linguist.service.translator.connector;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.errors.*;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUSW on 10/16/2018.
 */
@Component
public class RepositoryConnector {

    private static final String localizedFilesBase = "src/main/webapp/i18n/en/";
    private static final String localizedFilesPattern = "([^(_)]*).json";

    public List<String> findLocalizedProperties(String repositoryLocation, String targetLocation) throws  Exception {
        List<String> localizedFilesPath = new ArrayList<String>();
        File file = new File(targetLocation);
        Git git = null;
        try {
            try {

                git = Git.cloneRepository()
                        .setURI(repositoryLocation)
                        .setDirectory(file)
                        .call();
            }  catch (Exception e) {
                git = Git.open(file);
            }

            Repository repository = git.getRepository();

            ObjectId lastCommitId = repository.resolve(Constants.HEAD);

            TreeWalk treeWalk = new TreeWalk(repository);
            treeWalk.addTree(new RevWalk(repository).parseTree(lastCommitId));
            treeWalk.setRecursive(false);
            while (treeWalk.next()) {
                if (treeWalk.isSubtree()) {
                    treeWalk.enterSubtree();
                } else {
                    String regex = localizedFilesBase + localizedFilesPattern;
                    if (treeWalk.getPathString().matches(regex)) {
                        localizedFilesPath.add(targetLocation + File.separatorChar + treeWalk.getPathString());
                        System.out.println("file: " + treeWalk.getPathString());
                    }
                }
            }

        } catch (Exception e){
            throw e;
        }

        return localizedFilesPath;
    }

    public void pushCommand(String sourceFolder, String repoUrl, String commitMessage, String usrName, String password) throws Exception{
        try (Git git = Git.open(new File(sourceFolder))) {

            RemoteAddCommand remoteAddCommand = git.remoteAdd();
            remoteAddCommand.setName("origin");
            remoteAddCommand.setUri(new URIish(repoUrl));
            remoteAddCommand.call();

            git.add().addFilepattern(".").call();
            git.commit().setMessage(commitMessage).call();

            PushCommand pushCommand = git.push();
            pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(usrName, password));
            pushCommand.call();

        } catch (Exception e){
            throw e;
        }
    }

}
