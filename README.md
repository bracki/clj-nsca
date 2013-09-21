# clj-nsca

The project uses [Midje](https://github.com/marick/Midje/).

## How to run the tests

`lein midje` will run all tests.

`lein midje namespace.*` will run only tests beginning with "namespace.".

`lein midje :autotest` will run all the tests indefinitely. It sets up a
watcher on the code files. If they change, only the relevant tests will be
run again.

## Installing dependencies locally

Unfortunately `jsendnsca` is not available via Maven.
So I'm using http://www.pgrs.net/2011/10/30/using-local-jars-with-leiningen/.

Hence:
 
    mkdir mvn-repo
    cd ..
    svn checkout http://jsendnsca.googlecode.com/svn/trunk/ jsendnsca-read-only
    cd jsendnsca-read-only
    cd jsendnsca
    mvn install
    mvn install:install-file -Dfile=target/jsendnsca-2.1-SNAPSHOT.jar -DartifactId=jsendnsca -Dversion=2.1-SNAPSHOT -DgroupId=com.googlecode.jsendnsca -Dpackaging=jar -DlocalRepositoryPath=../clj-nsca/mvn-repo
    cd ..
    cd clj-nsca
